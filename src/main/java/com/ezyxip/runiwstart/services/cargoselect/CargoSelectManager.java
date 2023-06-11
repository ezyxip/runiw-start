package com.ezyxip.runiwstart.services.cargoselect;

import com.ezyxip.runiwstart.entities.*;
import com.ezyxip.runiwstart.services.AgentContainer;
import com.ezyxip.runiwstart.services.AgentType;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.OperationManager;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class CargoSelectManager implements OperationManager {

    private final String ID;
    private UserEntity employer;
    private AreaEntity area;
    private OrderEntity order;
    boolean check = true;
    boolean enable = true;

    public CargoSelectManager(){
        ID = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public AgentContainer getAgentContainer(String username) {
        if(employer.getUsername().equals(username)){
            return new AgentContainer(
                    check ? AgentType.UNCONFIRMED : AgentType.CONFIRMED,
                    new CargoSelectAgent(this, username),
                    "Отбор груза",
                    "Отбор груза на площадку " + area.getName()
            );
        }
        return null;
    }

    @Override
    public void confirmWorkStart(String username) {
        if (employer.getUsername().equals(username)){
            check = false;
            try {
                save();
            } catch (IOException e) {
                throw new RuntimeException("Не удалось сохранить менеджер отбора после соглашения к работе сотрудника: " + username);
            }
        }
    }

    @Override
    public boolean complete(String username) {
        if(employer.getUsername().equals(username)) {
            List<CargoBookingEntity> booking = DataStorage.cargoBookingRepository.findAllByOrders(order);
            for (CargoBookingEntity i : booking) {
                if (i.getCount() == i.getCargounit().getCount()) {
                    DataStorage.cargounitRepository.deleteById(i.getId());
                } else {
                    CargounitEntity newCargo = new CargounitEntity(
                            i.getCargounit().getCargotype(),
                            i.getCargounit().getCell(),
                            i.getCargounit().getAcceptance(),
                            i.getCargounit().getCount() - i.getCount()
                    );
                    newCargo.setId(i.getCargounit().getId());
                    DataStorage.cargounitRepository.save(newCargo);
                }
                DataStorage.cargoBookingRepository.delete(i);
            }
            stop();
        }else return false;

        return true;
    }

    @Override
    public void reserveResource() {
        area.setBooking(false);
        DataStorage.areaRepository.save(area);
        employer.setBooking(false);
        DataStorage.userRepository.save(employer);
        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить менеджер отбора после резервирования ресурсов");
        }
    }

    @Override
    public String getTitle() {
        return String.format("Отбор товара для заказа <%s : %s>", order.getCustomer(), order.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public void stop() {
        area.setBooking(true);
        DataStorage.areaRepository.save(area);
        employer.setBooking(true);
        DataStorage.userRepository.save(employer);

        DataStorage.managerRepository.deleteById(ID);
        enable = false;
        employer = new UserEntity("___", "___");
    }

    @Override
    public void save() throws IOException {
        OperationManagerEntity operationManagerEntity = new OperationManagerEntity(
                this.getId(),
                OperationManager.serialize(this)
        );
        DataStorage.managerRepository.save(operationManagerEntity);
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    public UserEntity getEmployer() {
        return employer;
    }

    public void setEmployer(UserEntity employer) {
        this.employer = employer;
    }

    public AreaEntity getArea() {
        return area;
    }

    public void setArea(AreaEntity area) {
        this.area = area;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CargoSelectManager{" +
                "ID='" + ID + '\'' +
                ", employer=" + employer +
                ", area=" + area +
                ", order=" + order +
                ", check=" + check +
                ", enable=" + enable +
                '}';
    }
}
