package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.CargoBookingEntity;
import com.ezyxip.runiwstart.entities.CargotypeEntity;
import com.ezyxip.runiwstart.entities.CargounitEntity;
import com.ezyxip.runiwstart.entities.OrderEntity;
import com.ezyxip.runiwstart.services.DataStorage;
import com.ezyxip.runiwstart.services.NomenclaturePosition;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderScreen extends TabSheet {

    Grid<OrderEntity> requestGrid = new Grid<>(OrderEntity.class, false);
    public OrderScreen(){
        Tab requests = new Tab("Заявки");
        Tab orders = new Tab("Заказы");
        add(requests, requestsContent());
        add(orders, orderContent());
    }

    private VerticalLayout orderContent(){
        VerticalLayout res = new VerticalLayout();

        Grid<OrderEntity> orderGrid = new Grid<>(OrderEntity.class, false);
        orderGrid.setAllRowsVisible(true);

        Button refresh = new Button(new Icon(VaadinIcon.REFRESH));
        refresh.addClickListener(e -> orderGrid.setItems(DataStorage.orderRepository.findAllByIsapproved(true)));

        Button addNewOrder = new Button("Создать вручную");
        addNewOrder.addClickListener( e -> newOrderEditor());

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(refresh, addNewOrder);

        gridCreate(orderGrid);

        orderGrid.setItems(DataStorage.orderRepository.findAllByIsapproved(true));

        orderGrid.addSelectionListener(e -> {
            orderDialog(e.getFirstSelectedItem().orElse(null));
        });

        res.add(buttonLayout, orderGrid);

        return res;
    }

    private void gridCreate(Grid<OrderEntity> orderGrid) {
        orderGrid.addColumn(OrderEntity::getCustomer).setHeader("Заказчик");
        orderGrid.addColumn(o -> o.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).setHeader("Дата заказа");
        orderGrid.addColumn(new ComponentRenderer<>(
                ()->new Button(new Icon(VaadinIcon.INFO)),
                (button, order) ->{
                    button.addClickListener(e -> {
                        requestInfo(order);
                    });
                })).setHeader("Детали");
    }

    private void newOrderEditor() {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Ручное создание заказа");

        Button close = new Button(new Icon(VaadinIcon.CLOSE));
        close.addClickListener(e -> dialog.close());

        dialog.getHeader().add(close);

        VerticalLayout customerNameInput = new VerticalLayout();

        TextField customer = new TextField("Заказчик");
        customer.setWidth("100%");

        Button customerSubmit = new Button("Ввести");
        customerSubmit.setWidth("100%");

        customerNameInput.add(customer, customerSubmit);

        dialog.add(customerNameInput);

        dialog.open();

        VerticalLayout orderEdit = new VerticalLayout();

        List<CargotypeEntity> types = DataStorage.cargotypeRepository.findAll();

        TextField count = new TextField(String.format("Количество (%s)", types.get(0).getUnittype().getName()));
        count.setWidth("100%");
        ComboBox<CargotypeEntity> typeOfCargo = new ComboBox<>("Тип груза");
        typeOfCargo.setWidth("100%");
        typeOfCargo.setItems(types);
        typeOfCargo.setValue(types.get(0));
        typeOfCargo.addValueChangeListener(e -> {
            count.setLabel(String.format("Количество (%s)", e.getValue().getUnittype().getName()));
        });

        Button addNom = new Button("Добавить пункт");
        addNom.setWidth("100%");

        ArrayList<NomenclaturePosition> noms = new ArrayList<>();

        Grid<NomenclaturePosition> grid = new Grid<>(NomenclaturePosition.class, false);
        grid.setWidth("100%");
        grid.setAllRowsVisible(true);
        grid.addColumn(NomenclaturePosition::getCargoType).setHeader("Тип");
        grid.addColumn(NomenclaturePosition::getFormattedCount).setHeader("Количество");
        grid.addColumn(new ComponentRenderer<>(
                () -> new Button(new Icon(VaadinIcon.CLOSE)),
                (button, nom) ->{
                    button.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    button.addClickListener(e -> {
                        noms.remove(nom);
                        grid.setItems(noms);
                    });
                }
        ));

        grid.setItems(noms);

        addNom.addClickListener(e -> {
           NomenclaturePosition nomenclaturePosition = new NomenclaturePosition(
                   typeOfCargo.getValue(), Integer.parseInt(count.getValue())
           );
           noms.add(nomenclaturePosition);
           grid.setItems(noms);
        });

        orderEdit.add(typeOfCargo, count, addNom, grid);

        customerSubmit.addClickListener(e -> {
            dialog.removeAll();
            dialog.add(orderEdit);
            Button createOrder = new Button("Создать заказ");
            createOrder.addClickListener(ev -> {
                OrderEntity order = new OrderEntity();
                try {
                    order.setNomenclature(noms);
                    order.setCustomer(customer.getValue());
                    order.setDate(LocalDateTime.now());
                    DataStorage.orderRepository.save(order);
                    reserveCargo(order);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                dialog.close();
            });
            dialog.getFooter().add(createOrder);
        });

    }

    private void orderDialog(OrderEntity order){

    }

    private VerticalLayout requestsContent(){
        VerticalLayout res = new VerticalLayout();

        requestGrid.setAllRowsVisible(true);

        gridCreate(requestGrid);

        requestGrid.setItems(DataStorage.orderRepository.findAllByIsapproved(false));

        requestGrid.addSelectionListener(e -> {
            requestDialog(e.getFirstSelectedItem().orElse(null));
        });

        res.add(requestGrid);
        return res;
    }

    private void requestInfo(OrderEntity order){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Детали заявки");

        Button close = new Button(new Icon(VaadinIcon.CLOSE));
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener(e -> dialog.close());

        dialog.getHeader().add(close);

        ByteArrayInputStream bis = new ByteArrayInputStream(order.getDetails());
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            List<NomenclaturePosition> noms = (List<NomenclaturePosition>) ois.readObject();

            Grid<NomenclaturePosition> grid = new Grid<>(NomenclaturePosition.class, false);
            grid.setHeight("300px");
            grid.setMinWidth("300px");

            grid.addColumn(NomenclaturePosition::getCargoType).setHeader("Груз");
            grid.addColumn(NomenclaturePosition::getFormattedCount).setHeader("Количество");

            grid.setItems(noms);

            dialog.add(grid);

            dialog.open();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void requestDialog(OrderEntity order){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Утверждениие");

        dialog.add(new Label("Утвердить заявку?"));

        Button confirm = new Button("Утвердить");
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        confirm.addClickListener(e->{
           reserveCargo(order);
            requestGrid.setItems(DataStorage.orderRepository.findAllByIsapproved(false));
            dialog.close();
        });

        Button cancel = new Button("Отмена");
        cancel.addClickListener(e -> dialog.close());

        dialog.getFooter().add(confirm, cancel);
        dialog.open();
    }

    void reserveCargo(OrderEntity order){
        try {
            List<NomenclaturePosition> noms = order.getNomenclature();
            for(NomenclaturePosition np : noms){
                List<CargounitEntity> cargos = DataStorage.cargounitRepository.findAllByCargotype(np.getCargotypeEntity());
                int nomCount = np.getCount();
                for(CargounitEntity cu : cargos){
                    List<CargoBookingEntity> cargoBooking = cu.getBooking();
                    int cargoCount = cu.getCount();
                    for(CargoBookingEntity cb : cargoBooking){
                        cargoCount -= cb.getCount();
                    }
                    if(cargoCount > 0){
                        nomCount -= cargoCount;
                        CargoBookingEntity cargoBookingEntity = new CargoBookingEntity(
                                cu, order, nomCount < 0 ? nomCount + cargoCount : cargoCount
                        );
                        DataStorage.cargoBookingRepository.save(cargoBookingEntity);
                    }
                    if(nomCount <= 0 ) break;
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        order.setIsapproved(true);
        DataStorage.orderRepository.save(order);
    }
}
