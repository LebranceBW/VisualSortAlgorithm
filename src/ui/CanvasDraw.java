package ui;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CanvasDraw {
	
    private static double xScale;
    private static double yScale;
	public static void associateWithList(Canvas canvas, List<Integer> backupList2)
    {
    	// 定标横轴刻度
    	int xRange = backupList2.size();
    	if (xRange==0)
    		return;
    	xScale = (double)canvas.getWidth() / xRange;
    	// 定标纵轴刻度
    	int yRange = 100;
    	yScale = (double)canvas.getHeight() / (yRange);
    }
    public static void draw(Canvas canvas, List<Integer> lst)
    {
    	// 根据内容画图
    	GraphicsContext gc = canvas.getGraphicsContext2D();
   		gc.setFill(Color.valueOf("#009999"));
    	for(int i=0;i< lst.size();i++)
    	{
    		int y = lst.get(i);
    		gc.fillRect(i*xScale+1, canvas.getHeight() - y*yScale+1, xScale-2, y*yScale-2);
    		gc.strokeRect(i*xScale, canvas.getHeight() - y*yScale, xScale, y*yScale);
    	}
    }
    public static void clearCanvas(Canvas canvas)
    {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    public static void drawColumn(Canvas canvas, int index, int y, Paint p)
    {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
   		gc.setFill(p);
   		gc.fillRect(index*xScale+1, canvas.getHeight() - y*yScale+1, xScale-2, y*yScale-2);
   		gc.strokeRect(index*xScale, canvas.getHeight() - y*yScale, xScale, y*yScale);
    }
    public static void printInfo(Canvas canvas, int count)
    {
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.setFill(Color.BLACK);
    	gc.setFont(new Font("Arial", 20));
    	gc.fillText(String.format("Total swap counts:%d", count), 20, 40);
    	gc.fillText(String.format("Frame rate:%d", 30), 20, 60);
    }
}
