package se233.project;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class ImageModel {
    private final ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();
    private String path;
    private String imageName;
    private final StringProperty waterMarkColor = new SimpleStringProperty();
    private final StringProperty waterMarkFont = new SimpleStringProperty();
    private final IntegerProperty waterMarkSize = new SimpleIntegerProperty();
    private final StringProperty waterMarkText = new SimpleStringProperty();
    private final ObjectProperty<Object> isChange = new SimpleObjectProperty<>();
    private final DoubleProperty opacityProperty = new SimpleDoubleProperty();
    private final DoubleProperty rotationProperty = new SimpleDoubleProperty();
    private final BooleanProperty keepRatioProperty = new SimpleBooleanProperty();
    private final IntegerProperty widthProperty = new SimpleIntegerProperty();
    private final IntegerProperty heightProperty = new SimpleIntegerProperty();
    private final IntegerProperty percentProperty = new SimpleIntegerProperty();

    public ImageModel() {
        // Default constructor
    }

    public ImageModel(String path, String waterMarkColor, int waterMarkSize, String waterMarkFont,
                      double opacity, Image image, String imageName, boolean keepRatio,
                      int width, int height) {
        this.path = path;
        this.waterMarkColor.set(waterMarkColor);
        this.waterMarkSize.set(waterMarkSize);
        this.waterMarkFont.set(waterMarkFont);
        this.opacityProperty.set(opacity);
        this.imageProperty.set(image);
        this.imageName = imageName;
        this.keepRatioProperty.set(keepRatio);
        this.widthProperty.set(width);
        this.heightProperty.set(height);
        this.percentProperty.set(100);
    }

    // Getters and setters for all properties

    public Image getImage() {
        return imageProperty.get();
    }

    public ObjectProperty<Image> imageProperty() {
        return imageProperty;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImageName() {
        return imageName;
    }

    public String getWaterMarkColor() {
        return waterMarkColor.get();
    }

    public StringProperty waterMarkColorProperty() {
        return waterMarkColor;
    }

    public String getWaterMarkFont() {
        return waterMarkFont.get();
    }

    public StringProperty waterMarkFontProperty() {
        return waterMarkFont;
    }

    public int getWaterMarkSize() {
        return waterMarkSize.get();
    }

    public IntegerProperty waterMarkSizeProperty() {
        return waterMarkSize;
    }

    public String getWaterMarkText() {
        return waterMarkText.get();
    }

    public StringProperty waterMarkTextProperty() {
        return waterMarkText;
    }

    public Object getIsChange() {
        return isChange.get();
    }

    public ObjectProperty<Object> isChangeProperty() {
        return isChange;
    }

    public double getOpacity() {
        return opacityProperty.get();
    }

    public DoubleProperty opacityProperty() {
        return opacityProperty;
    }

    public double getRotation() {
        return rotationProperty.get();
    }

    public DoubleProperty rotationProperty() {
        return rotationProperty;
    }

    public boolean isKeepRatio() {
        return keepRatioProperty.get();
    }

    public BooleanProperty keepRatioProperty() {
        return keepRatioProperty;
    }

    public int getWidth() {
        return widthProperty.get();
    }

    public IntegerProperty widthProperty() {
        return widthProperty;
    }

    public int getHeight() {
        return heightProperty.get();
    }

    public IntegerProperty heightProperty() {
        return heightProperty;
    }

    public int getPercent() {
        return percentProperty.get();
    }

    public IntegerProperty percentProperty() {
        return percentProperty;
    }
}
