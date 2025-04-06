package com.example.demo2;

import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.Line;
import javax.swing.text.Position;
import java.io.IOException;

public class Game extends Application {
        private String BorderPane_Style = "-fx-background-color:#13272b;";
        private String Button_Style = "-fx-background-color:#962321; -fx-text-fill: white;";
        private String Button_Style_Hover = "-fx-background-color:red; -fx-text-fill: white";
        private String ContactStyleQuestion = "-fx-text-fill:white; -fx-background-color:#1e899e; -fx-font-size:30px; -fx-letter-spacing: 13px;";
        private String ContactStyleOptions = "-fx-text-fill:white; -fx-background-color:#2b494f;";
        /*------------------*/
        private String[] questions = {"What's the error ?","what's correct ?"};
        private String[][] options = {{"-fx-color:red","fx-color:RED","-fx-font-size:34px"},{"VBox","hbOX","staKPane"}};
        private int[] correctAnswers   = {1,0};
        private int currentQuestion = 0; // مؤشر السؤال الحالي
        private int score = 0; // عدد الإجابات الصحيحة


        @Override
        public void start(Stage stage) {
                Label questionLabel = new Label(questions[currentQuestion]); // عرض السؤال
                VBox layout = new VBox(38);
                layout.getChildren().add(questionLabel);
                layout.setAlignment(Pos.CENTER);
                layout.setStyle(BorderPane_Style);

                // إنشاء خيارات الإجابة
                for (int i = 0; i < options[currentQuestion].length; i++) {
                        Button optionButton = new Button(options[currentQuestion][i]);
                        int selectedOption = i; // لتحديد الفهرس الصحيح
                        optionButton.setOnAction(e -> handleAnswer(selectedOption, questionLabel, layout));
                        layout.getChildren().add(optionButton);
                }


                Scene secondScene = new Scene(layout, 600, 600);
                /*---------------------------------------*/
                //style الشاشة الثانية
                questionLabel.setStyle(ContactStyleQuestion);

                /*---------------------------------------*/

         //الشاشة الثانية ↖️
        /*--------------------*/


        // الشاشة الاولى ↘️
        /*--------------------*/
        Text gameText = new Text("Coding Game 🎮"); // اسم العبة
        Text contact = new Text("Welcome To Our QuizGame");
        Text info = new Text("This game about computer language and should be answer correctly good lock 😊");
        info.setFill(Color.WHITE);
        Button btnStart = new Button("Start");
        Button finish = new Button("Exit");
        contact.setFont(Font.font("Impact",20));
        gameText.setFont(Font.font("Impact", 37));
        gameText.setFill(Color.RED);
        contact.setStyle("-fx-fill: red; -fx-font-size:24px");
        btnStart.setPrefSize(150,30);
        btnStart.setStyle(Button_Style);
        finish.setStyle(Button_Style);
        DropShadow shadowText = new DropShadow();
        shadowText.setOffsetX(7);
        shadowText.setOffsetY(7);
        shadowText.setColor(Color.BLACK);
        gameText.setEffect(shadowText);
        DropShadow shadowcontect = new DropShadow();
        shadowText.setOffsetX(7);
        shadowText.setOffsetY(7);
        shadowText.setColor(Color.BLACK);
        contact.setEffect(shadowcontect);
        /*-------------------------------------------------*/
        // ترتيب  game text and img
        ImageView imgGame = new ImageView(new Image("igame.png"));// صورة العبة جنب الاسم
        imgGame.setFitHeight(130);// ارتفاع الصورة
        imgGame.setFitWidth(130);// عرض الصورة
        imgGame.setRotate(-10); // دوران الصورة
        imgGame.setPreserveRatio(true); // الحفاظ على وزن الصورة
        gameText.setX(180); // موقع  text على محور x
        gameText.setY(100); // موقع  text على محور y
        imgGame.setX(120);// موقع img على محور x
        imgGame.setY(30);// موقع  img على محور y
        /*------------------------*/
        //effects
        btnStart.setOnAction(e -> stage.setScene(secondScene));
        finish.setOnMouseEntered(e->finish.setStyle(Button_Style_Hover));
        finish.setOnMouseExited(e->finish.setStyle(Button_Style));
        TranslateTransition shake = new TranslateTransition(Duration.seconds(0.1), btnStart);
        shake.setByX(1); // مقدار الحركة على المحور X
        shake.setCycleCount(7); // عدد مرات التكرار
        shake.setAutoReverse(true); // عكس الحركة تلقائيًا
        // حدث الموشر: بد الاهتزاز عند التمرير
        btnStart.setOnMouseEntered(e -> shake.play());
        finish.setOnAction(e -> Platform.exit());
        FillTransition colorTransition = new FillTransition();
        colorTransition.setShape(gameText); // العنصر يتم تطبيق التأثير عليه
        colorTransition.setDuration(Duration.seconds(2)); // مدة التغيير
        colorTransition.setFromValue(Color.RED); // اللون الأول
        colorTransition.setToValue(Color.LAVENDERBLUSH); // اللون الثاني
        colorTransition.setCycleCount(FillTransition.INDEFINITE); // التكرار بلا نهاية
        colorTransition.setAutoReverse(true); // عكس الحركة تلقائيا
        // بدا الحركة
        colorTransition.play();
        /*------------------------*/
        // الحاويات
        Pane container = new Pane();
        container.getChildren().addAll(imgGame,gameText); // ==>> للاسم والشعار واقدر اتحكم فيهم
        VBox vboxContact = new VBox(90);
        vboxContact.getChildren().addAll(container,contact,info,btnStart,finish);
        vboxContact.setAlignment(Pos.CENTER);
        vboxContact.setStyle(BorderPane_Style);
        vboxContact.setPadding(new Insets(5,5,45,0));

        /*-----------------------------------------------------------------------------*/

        Scene scene = new Scene(vboxContact, 600, 600);
        stage.setResizable(false); // ما يسمح user يكبر الشاشة
        stage.setScene(scene);
        stage.setTitle("QuizGame");
        stage.show();

    }

        private void handleAnswer(int selectedOption, Label questionLabel, VBox layout) {
                // التحقق من الإجابة
                if (selectedOption == correctAnswers[currentQuestion]) {
                        score++; // زيادة النقاط إذا كانت الإجابة صحيحة
                }
                currentQuestion++; // الانتقال للسؤال التالي

                // التحقق من نهاية الأسئلة
                if (currentQuestion >= questions.length) {
                        questionLabel.setText("تم الانتهاء من الأسئلة! مجموعك: " + score);
                        layout.getChildren().clear();
                        layout.getChildren().add(questionLabel); // عرض النتيجة النهائية
                } else {
                        // تحديث السؤال وخيارات الإجابة
                        questionLabel.setText(questions[currentQuestion]);
                        layout.getChildren().clear();
                        layout.getChildren().add(questionLabel);
                        for (int i = 0; i < options[currentQuestion].length; i++) {
                                Button optionButton = new Button(options[currentQuestion][i]);
                                int updateoptions = i; // لتحديد الفهرس الصحيح
                                optionButton.setOnAction(e -> handleAnswer(updateoptions, questionLabel, layout));
                                layout.getChildren().add(optionButton);

                        }

                }

        }

        public static void main(String[] args) {
        launch();
    }
}