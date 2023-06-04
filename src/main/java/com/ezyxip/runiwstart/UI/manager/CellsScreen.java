package com.ezyxip.runiwstart.UI.manager;

import com.ezyxip.runiwstart.entities.CellEntity;
import com.ezyxip.runiwstart.repositories.CellRepository;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridDataView;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.util.StreamUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CellsScreen extends AppLayout {
    static Logger logger = Logger.getLogger(CellsScreen.class.getName());
    Grid<CellEntity> grid = new Grid<>(CellEntity.class, false);
    public CellsScreen(CellRepository repository){
        Iterable<CellEntity> cells = repository.findAll();
        List<CellEntity> cellEntityList = StreamSupport.stream(cells.spliterator(), false).collect(Collectors.toList());
        grid.addColumn(CellEntity::getName).setHeader("Ячейка").setFooter(String.valueOf(cellEntityList.size())).setSortable(true);
        grid.addColumn(cell -> {return cell.getRack_id().getName();}).setHeader("Стеллаж").setSortable(true);
        grid.addColumn(cell -> cell.getRack_id().getZone_id().getName()).setHeader("Зона").setSortable(true);

        GridListDataView<CellEntity> dataView = grid.setItems(cellEntityList);

        VerticalLayout layout = new VerticalLayout();

        TextField searchField = new TextField();
        searchField.setPlaceholder("Поиск");
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.getStyle()
                .set("width", "50%")
                .set("min-width", "15em");
        searchField.addValueChangeListener(event -> {dataView.refreshAll();});

        layout.add(searchField);
        layout.add(grid);

        dataView.addFilter(cellEntity -> {
            String filterText = searchField.getValue().trim();

            if(filterText.isEmpty())
                return true;

            String[] filterWords = filterText.split(" ");
            List<Boolean> res = Arrays.stream(filterWords)
                    .map(w ->
                            cellEntity.getName().contains(w)
                                    || cellEntity.getRack_id().getName().contains(w)
                                    || cellEntity.getRack_id().getZone_id().getName().contains(w))
                    .toList();


            logger.info("----------------------------------------");

            return res.stream().reduce((a,b) -> a&&b).orElse(false);
        });

        setContent(layout);
    }
}
