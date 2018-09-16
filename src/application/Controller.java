package application;

import event.ListValueChangedEvent;
import event.ListValueChangedListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class Controller implements ListValueChangedListener {

    @FXML
    private Canvas canvas;
    private double xScale;
    private double yScale;
    public boolean hasAssociated = false;
    private MonitoredList lst;
    private int counts = 0;
    
    public void associateWithList(MonitoredList lst)
    {
    	this.lst = lst;
    	// �������̶�
    	int xRange = lst.size();
    	if (xRange==0)
    		return;
    	xScale = (double)this.canvas.getWidth() / xRange;
    	// ��������̶�
    	int yRange = 100;
    	yScale = (double) (this.canvas.getHeight() / (yRange));
    	hasAssociated = true;
    }
    public void draw()
    {
    	// �������ݻ�ͼ
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

}