package com.example.template4fx.component;

import com.example.template4fx.Keys;
import com.example.template4fx.control.dialog.ConfirmDialog;
import com.example.template4fx.control.dialog.ErrorDialog;
import com.example.template4fx.control.dialog.InfoDialog;
import com.example.template4fx.control.dialog.ProgressDialog;
import com.example.template4fx.control.dialog.ValuePicker;
import com.example.template4fx.facade.FileFacade;
import com.example.template4fx.model.File;
import com.example.template4fx.task.ProgressTask;
import com.example.template4fx.view.SVGTableCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class ExampleView
    extends MainView
{
    @FXML
    private TableView<FileFacade> fileView;

    @FXML
    private TableColumn<FileFacade, String> fileNameColumn;

    private final ObservableList<FileFacade> files = FXCollections.observableArrayList();

    @FXML
    private Button valuePickerButton;

    private final ObservableList<String>
        items =
        FXCollections.observableArrayList( "Abacate",
                                           "Abacaxi",
                                           "Ameixa",
                                           "Amora",
                                           "Araticum",
                                           "Atemoia",
                                           "Avocado",
                                           "Banana prata",
                                           "Caju",
                                           "Cana descascada",
                                           "Caqui",
                                           "Caqui Fuyu",
                                           "Carambola",
                                           "Cereja",
                                           "Coco verde",
                                           "Figo",
                                           "Figo da Índia",
                                           "Framboesa",
                                           "Goiaba",
                                           "Graviola",
                                           "Jabuticaba",
                                           "Jambo",
                                           "Jambo rosa",
                                           "Jambolão",
                                           "Kino (Kiwano)",
                                           "Kiwi",
                                           "Laranja Bahia",
                                           "Laranja para suco",
                                           "Laranja seleta",
                                           "Laranja serra d’água",
                                           "Laranjinha kinkan",
                                           "Lichia",
                                           "Lima da pérsia",
                                           "Limão galego",
                                           "Limão Taiti",
                                           "Maçã argentina",
                                           "Maçã Fuji",
                                           "Maçã gala",
                                           "Maçã verde",
                                           "Mamão formosa",
                                           "Mamão Havaí",
                                           "Manga espada",
                                           "Manga Haden",
                                           "Manga Palmer",
                                           "Manga Tommy",
                                           "Manga Ubá",
                                           "Mangostim",
                                           "Maracujá doce",
                                           "Maracujá para suco",
                                           "Melancia",
                                           "Melancia sem semente",
                                           "Melão",
                                           "Melão Net",
                                           "Melão Orange",
                                           "Melão pele de sapo",
                                           "Melão redinha",
                                           "Mexerica carioca",
                                           "Mexerica Murcote",
                                           "Mexerica Ponkan",
                                           "Mirtilo",
                                           "Morango",
                                           "Nectarina",
                                           "Nêspera ou ameixa amarela",
                                           "Noni",
                                           "Pera asiática",
                                           "Pera portuguesa",
                                           "Pêssego",
                                           "Physalis",
                                           "Pinha",
                                           "Pitaia",
                                           "Romã",
                                           "Tamarilo",
                                           "Tamarindo",
                                           "Uva red globe",
                                           "Uva rosada",
                                           "Uva Rubi",
                                           "Uva sem semente",
                                           "Abobora moranga",
                                           "Abobrinha italiana",
                                           "Abobrinha menina",
                                           "Alho",
                                           "Alho descascado",
                                           "Batata baroa ou cenoura amarela",
                                           "Batata bolinha",
                                           "Batata doce",
                                           "Batata inglesa",
                                           "Batata yacon",
                                           "Berinjela",
                                           "Beterraba",
                                           "Cebola bolinha",
                                           "Cebola comum",
                                           "Cebola roxa",
                                           "Cenoura",
                                           "Cenoura baby",
                                           "Couve flor",
                                           "Ervilha",
                                           "Fava",
                                           "Gengibre",
                                           "Inhame",
                                           "Jiló",
                                           "Massa de alho",
                                           "Maxixe",
                                           "Milho",
                                           "Pimenta biquinho fresca",
                                           "Pimenta de bode fresca",
                                           "Pimentão amarelo",
                                           "Pimentão verde",
                                           "Pimentão vermelho",
                                           "Quiabo",
                                           "Repolho",
                                           "Repolho roxo",
                                           "Tomate cereja",
                                           "Tomate salada",
                                           "Tomate sem acidez",
                                           "Tomate uva",
                                           "Vagem",
                                           "Agrião",
                                           "Alcachofra",
                                           "Alface",
                                           "Alface americana",
                                           "Almeirão",
                                           "Brócolis",
                                           "Broto de alfafa",
                                           "Broto de bambu",
                                           "Broto de feijão",
                                           "Cebolinha",
                                           "Coentro",
                                           "Couve",
                                           "Espinafre",
                                           "Hortelã",
                                           "Mostarda",
                                           "Rúcula",
                                           "Salsa",
                                           "Ovos brancos",
                                           "Ovos de codorna",
                                           "Ovos vermelhos" );

    public void initialize()
    {
        setTitle( message( "title.home" ) );
        setSvg( "home" );

        fileView.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );

        fileView.setItems( files );
        files.add( new FileFacade( new File( "Folder 1", "folder", 0 ) ) );
        files.add( new FileFacade( new File( "Folder 2", "folder", 0 ) ) );
        files.add( new FileFacade( new File( "Word Document 1", "docx", 1000 ) ) );
        files.add( new FileFacade( new File( "Word Document 2", "docx", 2000 ) ) );
        files.add( new FileFacade( new File( "PDF Document 1", "pdf", 3000 ) ) );
        files.add( new FileFacade( new File( "PDF Document 2", "pdf", 4000 ) ) );
        files.add( new FileFacade( new File( "Excel Document 1", "xlsx", 5000 ) ) );
        files.add( new FileFacade( new File( "Excel Document 2", "xlsx", 6000 ) ) );
        files.add( new FileFacade( new File( "Powerpoint Document 1", "pptx", 7000 ) ) );
        files.add( new FileFacade( new File( "Powerpoint Document 2", "pptx", 8000 ) ) );

        fileNameColumn.setCellFactory( callback -> new SVGTableCell<>() );
    }

    @Override
    public void handleKeyEvent( KeyEvent event )
    {
        if ( Keys.F5.match( event ) )
        {
            System.out.println( "Refreshing..." );
            event.consume();
        }
    }

    public void showInfoDialog()
    {
        showDialog( new InfoDialog( "Information Dialog", "Testing the new Info Dialog" ) );
    }

    public void showErrorDialog()
    {
        showDialog( new ErrorDialog( "Error Dialog", new RuntimeException( "Something went wrong" ) ) );
    }

    public void showConfirmDialog()
    {
        ConfirmDialog dialog = new ConfirmDialog( "Confirm Dialog", "Are you sure this is okay?" );
        dialog.setOnOK( () -> System.out.println( "Ok pressed" ) );
        dialog.setOnCancel( () -> System.out.println( "Cancel pressed" ) );
        showDialog( dialog );
    }

    public void showProgressDialog()
    {
        Task task = new ProgressTask( 10 );
        showDialog( new ProgressDialog( "Task in progress", task ) );
        run( task );
    }

    public void testValuePicker()
    {
        ValuePicker<String> valuePicker = new ValuePicker<>( valuePickerButton, items );
        showDialog( valuePicker );
    }
}
