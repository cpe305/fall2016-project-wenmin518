package main.java.edu.calpoly.cpe305.wenmin.ParkingApplication;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * User Grphic Interface.
 * 
 * @author wenmin518
 *
 */
public class FXDriver extends Application {
  private static ParkingPath parkingPath;
  private static ParkingStrJson structJson;
  private static Geoloc userLoc = new Geoloc(-1, -1);
  private static int cartype = -1;
  private static User user = new User(userLoc, cartype);


  /**
   * Check if the input is an integer.
   * 
   * @param str referring to the string to be compared
   * @return boolean referring to whether the string is an integer
   */
  public static boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
    } catch (NumberFormatException err) {
      return false;
    } catch (NullPointerException err) {
      return false;
    }
    // only got here if we didn't return false
    return true;
  }

  @Override
  public void start(Stage stage) {


    // load the image
    Image image = new Image("file:///Users/wenmin518/git/fall2016-project-wenmin518/parking.jpg");
    /**
     * style and variables.
     */
    double rectwidth = 300;
    double imageheight = 600;
    double labelheight = 50;
    double tabwidth = 70;

    /**
     * loading an hbox for image.
     */
    HBox imagebox = new HBox();
    imagebox.setPadding(new Insets(0, rectwidth, 0, rectwidth));
    ImageView iv1 = new ImageView();
    iv1.setImage(image);
    iv1.setFitHeight(imageheight);
    imagebox.getChildren().add(iv1);


    /**
     * create leftbox for the cartype input from user.
     */
    HBox leftbox = new HBox();
    TextField ta = new TextField();

    double inputwidth = 500;
    ta.setPrefWidth(inputwidth);
    ta.setPromptText("Enter the Parking Type: 1: Electric, 2: Compact, 3: Hadicap, 4: Normal");
    leftbox.setPadding(new Insets(0, rectwidth, 0, rectwidth));

    leftbox.getChildren().add(ta);



    /**
     * The rightbox contains the find the spot button.
     */

    double buttonwidth = 200;
    HBox rightbox = new HBox();
    rightbox.setPadding(new Insets(0, -rectwidth - buttonwidth, 0, -rectwidth - buttonwidth));
    Button btn = new Button("Find the Spot");
    btn.setPrefWidth(buttonwidth);
    btn.setPrefHeight(20);
    // Setting an action for the btn button

    rightbox.getChildren().add(btn);


    /**
     * The style for the bottom box label printing just add back group color to text.
     */
    double extraspace = 90;
    double textheight = image.getHeight() - imageheight + extraspace;
    Rectangle buttomrect = new Rectangle();
    buttomrect.setY(imageheight);
    buttomrect.setWidth(rectwidth + image.getWidth());

    buttomrect.setHeight(textheight);
    buttomrect.setFill(Color.YELLOW);

    /**
     * Button box included the information to be shown to user.
     */
    HBox buttombox = new HBox();
    Label text = new Label("Welcome to PolyParkTrack");
    buttombox.getChildren().add(text);
    buttombox.setAlignment(Pos.CENTER);


    /**
     * Border set ups.
     */
    BorderPane border = new BorderPane();
    border.setTop(imagebox);
    border.setLeft(leftbox);
    border.setRight(rightbox);
    border.setBottom(buttombox);


    /**
     * Hbox for rectangle and number of parking spots labels.
     */

    Label numlabel = new Label("Number of Parking Spots Available:");
    numlabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
    numlabel.setTextFill(Color.YELLOW);
    double numtitleheight = 130;
    double numtitlewidth = 10;


    /**
     * Creating new group.
     */
    Group root = new Group();
    Scene scene = new Scene(root, image.getWidth() + rectwidth, image.getHeight() + extraspace);
    numlabel.setPadding(new Insets(numtitleheight, numtitlewidth, numtitleheight, numtitlewidth));

    /**
     * Rectangle on the left to have more styles.
     */
    Rectangle rect = new Rectangle();
    rect.setWidth(rectwidth);
    rect.setHeight(scene.getHeight());
    rect.setFill(Color.GREEN);
    rect.getFill();

    /**
     * Vbox set up showing number of parking spots on each parking structure.
     */
    double vboxspace = 30;
    VBox vbox = new VBox(vboxspace);
    for (int i = 0; i < 7; i++) {
      vbox.setPadding(new Insets(labelheight + numtitleheight, tabwidth + numtitlewidth,
          labelheight + numtitleheight, tabwidth + numtitlewidth));
      String str = "Parking Structure " + (i + 1) + ": ";
      if (structJson.getStrcutre(i).getNumavailable() == 0) {
        str += "full";
      } else {
        str += structJson.getStrcutre(i).getNumavailable();
      }
      Label pslabel = new Label(str);
      pslabel.setTextFill(Color.YELLOW);
      pslabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
      vbox.getChildren().add(pslabel);
    }

    /**
     * adding different components to the stage
     */
    root.getChildren().add(buttomrect);
    HBox rectBox = new HBox();
    rectBox.getChildren().add(rect);
    root.getChildren().add(rectBox);
    root.getChildren().add(numlabel);
    root.getChildren().add(vbox);
    root.getChildren().add(border);


    /**
     * Stage labels and other info.
     */
    stage.setTitle("PolyParkTrack");
    stage.setWidth(image.getWidth());
    stage.setHeight(image.getHeight());
    stage.setScene(scene);
    stage.sizeToScene();


    /**
     * the mouse click allows user to pick location.
     */
    scene.setOnMouseClicked(e -> {
      Circle clicky = new Circle();
      if (e.getButton() == MouseButton.PRIMARY) {
        root.getChildren().clear();
        root.getChildren().add(buttomrect);
        root.getChildren().add(rectBox);
        root.getChildren().add(numlabel);
        root.getChildren().add(vbox);
        root.getChildren().add(border);
        clicky.setRadius(5);
        clicky.setFill(Color.TRANSPARENT);
        clicky.setStroke(Color.BLACK);
        if (e.getX() >= rectwidth && e.getX() <= (rectwidth + image.getWidth())) {
          if (e.getY() <= imageheight) {
            clicky.setCenterX(e.getX());
            clicky.setCenterY(e.getY());
            int userX = (int) ((e.getX() - rectwidth) / 10);
            int userY = (int) e.getY() / 10;
            System.out.println(userX + " " + userY);
            userLoc = new Geoloc(userX, userY);
          }
        }
        root.getChildren().addAll(clicky);
      } else {
        userLoc = new Geoloc(-1, -1);
      }
    });

    btn.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        if (userLoc.equals(new Geoloc(-1, -1))) {
          buttombox.getChildren().clear();
          buttombox.getChildren().add(new Label("Please Select a starting location."));
        } else {
          if (ta.getText() == null || ta.getText().isEmpty()) {
            cartype = 4;
            user = new User(userLoc, cartype);
            System.out.println(user.getCarType() + " " + user.getPosition().getX());
            Calculation cal = new Calculation(parkingPath.getAdj(), parkingPath.getVisited(), user,
                parkingPath.getRows(), parkingPath.getCols(), parkingPath.getNumVer(),
                structJson.getStructArr());
            String[] printinfo = cal.printInfo(userLoc).split("\n");
            buttombox.getChildren().clear();
            VBox info = new VBox();
            for (String content : printinfo) {
              Label printlabel = new Label(content);
              info.setAlignment(Pos.CENTER);
              info.getChildren().add(printlabel);
            }
            buttombox.getChildren().add(info);
          } else {
            if (ta.getText().equalsIgnoreCase("Normal")) {
              cartype = 4;

            } else if (ta.getText().equalsIgnoreCase("Handicap")) {
              cartype = 3;

            } else if (ta.getText().equalsIgnoreCase("Compact")) {
              cartype = 2;
            } else if (ta.getText().equalsIgnoreCase("Electric")) {
              cartype = 1;
            } else if (isInteger((String) ta.getText())) {
              cartype = (int) Integer.valueOf((String) ta.getText());
            }
            if (cartype < 1 || cartype > 4) {
              buttombox.getChildren().clear();
              buttombox.getChildren().add(new Label("Please Enter valid Parking Type"));
            } else {
              user = new User(userLoc, cartype);
              System.out.println(user.getCarType() + " " + user.getPosition().getX());
              Calculation cal = new Calculation(parkingPath.getAdj(), parkingPath.getVisited(),
                  user, parkingPath.getRows(), parkingPath.getCols(), parkingPath.getNumVer(),
                  structJson.getStructArr());
              String[] printinfo = cal.printInfo(userLoc).split("\n");
              buttombox.getChildren().clear();
              VBox info = new VBox();
              for (String content : printinfo) {
                Label printlabel = new Label(content);
                info.setAlignment(Pos.CENTER);
                info.getChildren().add(printlabel);
              }
              buttombox.getChildren().add(info);
            }
          }
          cartype = -1;
          userLoc = new Geoloc(-1, -1);
        }

      }

    });

    stage.show();
  }

  /**
   * Handling the clicks.
   * 
   * @param parent refers to the root
   * @param node refers to the node to be added
   */
  public void addEventHandler(Group parent, Node node) {
    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
      if (me.getButton().equals(MouseButton.SECONDARY)) {
        parent.getChildren().remove(node);
      }
    });
  }

  /**
   * main driver
   * 
   * @param args commandline arguments
   * @throws ParseException prevent from if json read is not working properly
   * @throws IOException Prevent from file not found.
   */
  public static void main(String[] args) throws ParseException, IOException {
    parkingPath = new ParkingPath();
    parkingPath.setadjFromFile("test.txt");
    structJson = new ParkingStrJson();
    structJson.setParkLocFromFile("JsonTest2.json");

    Application.launch(args);
  }
}

