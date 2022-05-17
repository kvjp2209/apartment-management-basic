package com.example.kvjp.service;

import com.example.kvjp.constant.EProcess;
import com.example.kvjp.dto.request.ReceivableRequestDto;
import com.example.kvjp.dto.response.ReceivableResponseDto;
import com.example.kvjp.model.*;
import com.example.kvjp.repository.ElectricBillRepository;
import com.example.kvjp.repository.ReceivableRepository;
import com.example.kvjp.repository.WaterBillRepository;
import com.example.kvjp.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

@Service
public class ReceivableService {
    @Autowired
    private WaterBillRepository waterBillRepository;

    @Autowired
    private ElectricBillRepository electricBillRepository;

    @Autowired
    private ReceivableRepository receivableRepository;

    @Autowired
    ElectricBillService electricBillService;

    @Autowired
    WaterBillService waterBillService;

    @Autowired
    EmailService emailService;

    @Autowired
    ServiceOtherService serviceOtherService;
//    public Receivable createReceivable(){
//        return ;
//    }

    //tính giá của bill hàm riêng biệt
    public int separatePricing(int oldNumber, int newNumber, int unit) {
        int calculate = (newNumber - oldNumber) * unit;
        return calculate;
    }

    @Transactional
    public Receivable createReceivable(ReceivableRequestDto receivableRequestDto, Leases leases) {
        ElectricBill electricBill = electricBillService.getByNameAndStatus(receivableRequestDto.getName(), EProcess.PROCESSING.getId());
        if (electricBill == null) {
            electricBill = new ElectricBill().builder()
                    .name("elec_" + receivableRequestDto.getName())
                    .status(EProcess.PROCESSING.getId())
                    .build();
        }

        WaterBill waterBill = waterBillService.getByNameAndStatus(receivableRequestDto.getName(), EProcess.PROCESSING.getId());
        if (waterBill == null) {
            waterBill = new WaterBill().builder()
                    .name("water_" + receivableRequestDto.getName())
                    .status(EProcess.PROCESSING.getId())
                    .build();
        }
        int electricPayment = separatePricing(electricBill.getElectricNumberOld(), electricBill.getElectricNumberNew(), electricBill.getUnit());

        int waterPayment = separatePricing(waterBill.getWaterNumberOld(), waterBill.getWaterNumberNew(), waterBill.getUnit());

        Set<ServiceOther> serviceOthers = serviceOtherService.getServiceListById(
                receivableRequestDto.getServices()
        );
        //tính tổng tiền những service đã chọn
        int servicePayment = serviceOtherService.getTotalServicePriceIn(serviceOthers);

        //tính tổng tiền tất cả phải chi
        int calculationPayment = electricPayment + waterPayment + leases.getApartment().getPrice()
                + servicePayment;

        Receivable receivable = new Receivable().builder()
                .name(receivableRequestDto.getName())
                .payment(calculationPayment)
                .status(EProcess.PROCESSING.getId())
                .service(serviceOthers)
                .electricBill(electricBill)
                .waterBill(waterBill)
                .leases(leases)
                .build();

        receivableRepository.save(receivable);

        return receivable;
    }

    public int calculatePayment(ElectricBill electricBill, WaterBill waterBill, Receivable receivable, Leases leases) {
        int electricPayment = separatePricing(electricBill.getElectricNumberOld(), electricBill.getElectricNumberNew(), electricBill.getUnit());

        int waterPayment = separatePricing(waterBill.getWaterNumberOld(), waterBill.getWaterNumberNew(), waterBill.getUnit());

        //tính tổng tiền những service đã chọn
        int servicePayment = serviceOtherService.getTotalServicePriceIn(receivable.getService());

        //tính tổng tiền tất cả phải chi
        int calculationPayment = electricPayment + waterPayment + leases.getApartment().getPrice()
                + servicePayment;

        return calculationPayment;
    }

    public String calculatePaymentForm(ElectricBill electricBill, WaterBill waterBill, Receivable receivable, Leases leases) {
        int electricPayment = separatePricing(electricBill.getElectricNumberNew(), electricBill.getElectricNumberOld(), electricBill.getUnit());

        int waterPayment = separatePricing(waterBill.getWaterNumberNew(), waterBill.getWaterNumberOld(), waterBill.getUnit());

        //tính tổng tiền những service đã chọn
        int servicePayment = serviceOtherService.getTotalServicePriceIn(receivable.getService());

        //tính tổng tiền tất cả phải chi
        int calculationPayment = electricPayment + waterPayment + leases.getApartment().getPrice()
                + servicePayment;

        String result = createBillForm(servicePayment, electricPayment, waterPayment, leases.getApartment().getPrice(), calculationPayment);

        return result;
    }

    public String createBillForm(int servicePayment, int electricPayment, int waterPayment, int calculationPayment, int apartmentPrice) {
        String result = "\n\t====Bill====\n"
                + "Service Payment: " + servicePayment
                + "\nElectric Payment: " + electricPayment
                + "\nWater Payment: " + waterPayment
                + "\nApartment Price: " + apartmentPrice
                + "\nTotal: " + calculationPayment;
        return result;
    }


    public Receivable getByIdReceivable(int id) {
        if (receivableRepository.findById(id).isPresent()) {
            return receivableRepository.findById(id).get();
        }
        return null;
    }

    public Receivable getByNameReceivable(String name) {
        Receivable receivable = receivableRepository.findByName(name);
        return receivable;
    }

    public Receivable getByNameReceivableAndStatus(String name, int status) {
        Receivable receivable = receivableRepository.findByNameAndStatus(name, status);
        return receivable;
    }

    public ReceivableResponseDto getDetailById(int id) {
        Receivable receivable = getByIdReceivable(id);
        if (receivable == null) {
            return null;
        }

        String payment = calculatePaymentForm(receivable.getElectricBill(), receivable.getWaterBill(), receivable, receivable.getLeases());

        ReceivableResponseDto receivableResponseDto = new ReceivableResponseDto(
                receivable.getId(),
                receivable.getName(),
                receivable.getCreateAt(),
                receivable.getUpdateAt(),
                receivable.getStatus(),
                receivable.getService(),
                receivable.getElectricBill(),
                receivable.getWaterBill(),
                receivable.getLeases(),
                payment
        );

        return receivableResponseDto;
    }

    public Receivable updateReceivable(ReceivableRequestDto receivableRequestDto, Receivable receivable) {
        int payment = calculatePayment(receivable.getElectricBill(), receivable.getWaterBill(), receivable, receivable.getLeases());

        if (!receivable.getName().equalsIgnoreCase(receivableRequestDto.getName()) && receivable.getStatus() == EProcess.PROCESSING.getId()) {
            if (checkDuplicateNameAtProcessing(receivableRequestDto.getName(), EProcess.PROCESSING.getId()) == true) {
                return null;
            }
            receivable.setName(receivableRequestDto.getName());
            receivable.setCreateAt(receivable.getCreateAt());
            receivable.setUpdateAt(receivableRequestDto.getUpdateAt());
            receivable.setPayment(payment);
            receivableRepository.save(receivable);
        }

        receivable.setName(receivableRequestDto.getName());
        receivable.setPayment(payment);
        receivableRepository.save(receivable);

        return receivable;
    }

    public boolean checkDuplicateNameAtProcessing(String name, int status) {
        if (receivableRepository.findByNameAndStatus(name, status) != null) {
            return true;
        }
        return false;
    }

    public void disableReceivable(Receivable receivable) {
        receivable.setStatus(EProcess.DONE.getId());
        receivable.getWaterBill().setStatus(EProcess.DONE.getId());
        receivable.getElectricBill().setStatus(EProcess.DONE.getId());
        receivableRepository.save(receivable);
    }

    public List<Receivable> findAllReceivables() {
        return receivableRepository.findAll();
    }

    public List<Receivable> findAllReceivablesByName(String name) {
        return receivableRepository.findAllByName(name);
    }


    public String listServiceOtherOnAReservation(Receivable receivable) {
        String s = "";
        for (ServiceOther serviceOther : receivable.getService()) {
            s += serviceOther.toString();
        }
        return s;
    }

    public void sendMailToUser(Receivable receivable) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
        System.out.println("Test: " + timeStamp);
        emailService.sendSimpleEmail(
                receivable.getLeases().getTenant().getEmail(),
                timeStamp + "[#ROOM_" +receivable.getLeases().getApartment().getName() + "] THÔNG BÁO HOÁ ĐƠN CẦN THANH TOÁN ",
                formatEmailReceivable(receivable)
                );
    }
    @Transactional
    public String formatEmailReceivable(Receivable receivable) {
        return
                "PHÍ DỊCH VỤ CĂN HỘ " + receivable.getLeases().getApartment().getName() +
                        "\nTên chủ hộ: " + receivable.getLeases().getTenant().getName() +
                        "\nNội dung cần thanh toán: \n" +
                        "\n- Giá phòng: " + receivable.getLeases().getApartment().getPrice() + "VND" +
                        "\n- Giá điện: " + receivable.getElectricBill().getUnit() + " (đơn vị: X/VND) - (Số cũ: " + receivable.getElectricBill().getElectricNumberOld() + " - Số mới: " + receivable.getElectricBill().getElectricNumberNew() + ")" +
                        "\n- Thành tiền: " + separatePricing(receivable.getElectricBill().getElectricNumberOld(), receivable.getElectricBill().getElectricNumberNew(), receivable.getElectricBill().getUnit()) +
                        "\n- Giá nước sạch: " + receivable.getWaterBill().getUnit() + " (đơn vị: X/VND) - (Số cũ: " + receivable.getWaterBill().getWaterNumberOld() + " - Số mới: " + receivable.getWaterBill().getWaterNumberNew() + ")" +
                        "\n- Thành tiền: " + separatePricing(receivable.getWaterBill().getWaterNumberOld(), receivable.getWaterBill().getWaterNumberNew(), receivable.getWaterBill().getUnit()) +
                        "\n- Các chi phí dịch phụ khác : " +
                        "\t" +
                        listServiceOtherOnAReservation(receivable) +
                        "\n=> TỔNG CỘNG: " + calculatePayment(
                        receivable.getElectricBill(), receivable.getWaterBill(), receivable, receivable.getLeases()
                ) + " VND"
                ;
    }
}
