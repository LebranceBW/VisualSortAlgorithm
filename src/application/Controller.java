package application;

import java.net.URL;
import java.util.ResourceBundle;

import event.ListValueChangedEvent;
import event.ListValueChangedListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Controller implements ListValueChangedListener,Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Button quickBtn;

    @FXML
    private ComboBox<AlgorithmType> Algorithm_selection;
    
    @FXML
    private Button playBtn;
    
    @FXML
    private TextField rateInput;
    
    private double xScale;
    private double yScale;
    public boolean hasAssociated = false;
    private MonitoredList lst;
    private int counts = 0;
    
    public void associateWithList(MonitoredList lst)
    {
    	this.lst = lst;
    	// 定标横轴刻度
    	int xRange = lst.size();
    	if (xRange==0)
    		return;
    	xScale = (double)this.canvas.getWidth() / xRange;
    	// 定标纵轴刻度
    	int yRange = 100;
    	yScale = (double) (this.canvas.getHeight() / (yRange));
    	hasAssociated = true;
    }
    public void draw()
    {
    	// 根据内容画图
    	GraphicsContext gc = canvas.getGraphicsContext2D();
   		gc.setFill(Color.valueOf("#009999"));
   		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    	for(int i=0;i< lst.size();i++)
    	{
    		int y = lst.get(i);
    		gc.fillRect(i*xScale+1, canvas.getHeight() - y*yScale+1, xScale-2, y*yScale-2);
    		gc.strokeRect(i*xScale, canvas.getHeight() - y*yScale, xScale, y*yScale);
    	}
    }
    public void drawColumn(int index, Paint p)
    {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
   		gc.setFill(p);
   		int y = lst.get(index);
   		gc.clearRect(index*xScale, 0, xScale, canvas.getHeight());
   		gc.fillRect(index*xScale+1, canvas.getHeight() - y*yScale+1, xScale-2, y*yScale-2);
   		gc.strokeRect(index*xScale, canvas.getHeight() - y*yScale, xScale, y*yScale);
    }
    public void printSwapCount(int count)
    {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.setFill(Color.BLACK);
    	gc.setFont(new Font("Arial", 20));
    	gc.fillText(String.format("Total swap counts:%d", count), 20, 40);
    	gc.fillText(String.format("Frame rate:%d", 30), 20, 60);
    }

	@Override
	public void onListValueChanged(ListValueChangedEvent e)
	{	
		counts++;
		draw();
		drawColumn(e.getIndex1(), Color.valueOf("#FF8100"));
		drawColumn(e.getIndex2(), Color.valueOf("#FF8100"));
		printSwapCount(counts);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		quickBtn.setOnMouseClicked(e -> Platform.exit());
		this.Algorithm_selection.getItems().addAll(
			AlgorithmType.BubbleSort,
			AlgorithmType.QuickSort,
			AlgorithmType.SelectionSort
				);
		this.playBtn.setOnMouseClicked(e->
		{
			SortMain sm = new SortMain();
			sm.ct = this;
			sm.launch(this.Algorithm_selection.getSelectionModel().getSelectedItem());
		});
	}

	
}