package lab10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private List<String> typeOfFile = new ArrayList<>();

    @FXML
    Button galleryPictureButton, ownPictureButton;
    @FXML
    TextField titleTextField;
    @FXML
    TextArea upTextArea, downTextArea;
    @FXML
    ImageView imageView;

    String file;
//    Image image;

    @FXML
    void singleFileChooserFromComputer(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",typeOfFile));
        File f = fileChooser.showOpenDialog(null);
        if (f != null) {
            file = f.getAbsolutePath();
            setImage();
        }
    }

    @FXML
    void singleFileChooserFromGallery(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",typeOfFile));
        fileChooser.setInitialDirectory(new File("src/main/resources/gallery"));
        File f = fileChooser.showOpenDialog(null);
        if (f != null) {
            file = f.getAbsolutePath();
            setImage();
        }
    }

    private void setImage() {
        String path = "file:///" + file;
        imageView.setImage(new Image(path));
    }

    @FXML
    void createMeme(ActionEvent event) {
        try {
            final BufferedImage image = ImageIO.read(new File(file));
            Graphics g = image.getGraphics();
            g.setFont(g.getFont().deriveFont(30f));
            g.drawString(upTextArea.getText(), 100, 100);
            g.drawString(downTextArea.getText(), 100, image.getHeight()-100);
            g.dispose();
            ImageIO.write(image, "jpg", new File(titleTextField.getText()+".png"));
            Meme meme = new Meme(new File(titleTextField.getText()+".png"), titleTextField.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeOfFile.add("*.jpeg");
        typeOfFile.add("*.jpg");
        typeOfFile.add("*.png");
        typeOfFile.add("*.gif");
    }
}
