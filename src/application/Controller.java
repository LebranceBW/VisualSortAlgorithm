package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sortAlgorithm.InsertionSort;
import sortAlgorithm.QuickSort;
import sortAlgorithm.SelectionSort;
import sortAlgorithm.ShellSort;
import sortAlgorithm.SortAlgorithm;
import swapUilitity.SwapOperation;
import ui.CanvasDraw;

public class Controller implements Initializable {

    @FXML
    private Canvas canvas;

    @FXML
    private Button quitBtn;

    @FXML
    private ComboBox<SortAlgorithm> Algorithm_selection;
    
    @FXML
    private Button playBtn;
    
    @FXML
    private TextField rateInput;

    @FXML
    private Button stopBtn;

    
    private List<Integer> toSortedList = null;
    private int counts = 0;
//	private long sysDate = System.currentTimeMillis();
//    private AnimationTimer at = null;
    private ScheduledService<Void> s = null;
    
    public void generateList()
    {
    	toSortedList = new ArrayList<Integer>();
		Random rd = new Random(System.currentTimeMillis());
		final int size = 100;
		for(int i = 0;i < size;i++)
			toSortedList.add(i+1);
		for(int i=size;i>0;i--)
		{
			int randIndex = rd.nextInt(i);
			int temp = toSortedList.get(randIndex);
			toSortedList.set(randIndex, toSortedList.get(i-1));
			toSortedList.set(i-1, temp);
		}
    }
    
    private void changeState(MachineState nextState)
    {
    	switch(nextState)
    	{
    		case InitState:
    		{
    			generateList();
				counts = 0;
				CanvasDraw.clearCanvas(canvas);
    			CanvasDraw.associateWithList(canvas, toSortedList);
    			CanvasDraw.draw(this.canvas, toSortedList);
    			playBtn.setDisable(false);
    			Algorithm_selection.setDisable(false);
    			rateInput.setDisable(false);
    			stopBtn.setDisable(false);
    			break;
    		}
    		case RunningState:
    		{
    			int rate = 60;
    			try
    			{
    				rate = Integer.valueOf(rateInput.getText());
    				if(!(rate > 0 && rate <= 90))
    					rate = 60;
    			}
    			catch (NumberFormatException e2) {
    				rate  = 60;
    			}
    			rateInput.setText(String.valueOf(rate));
    			playBtn.setDisable(true);
    			rateInput.setDisable(true);
    			stopBtn.setDisable(true);
    			
    			Deque<SwapOperation> opreations = this.Algorithm_selection.getSelectionModel().getSelectedItem().recordedSort(toSortedList);
    
    			s = new ScheduledService<Void>() {
					private long lastTime = 0;
					@Override
					protected Task<Void> createTask() {
						return new Task<Void>() {

							@Override
							protected Void call() throws Exception {
								long now = System.currentTimeMillis();
								if(!opreations.isEmpty())
								{
									SwapOperation swo = opreations.poll();
		    						counts++;
		    						int frameRate = (int) (1e3 / (now - lastTime));
		    						lastTime = now;
		    						toSortedList.set(swo.previousIndex, swo.previousNumber);
		    						toSortedList.set(swo.afterIndex, swo.afterNumber);
		    						CanvasDraw.clearCanvas(canvas);
		    						CanvasDraw.draw(canvas, toSortedList);
		    						CanvasDraw.drawColumn(canvas, swo.previousIndex, swo.previousNumber, Color.valueOf("#FF8100"));
		    						CanvasDraw.drawColumn(canvas, swo.afterIndex, swo.afterNumber, Color.valueOf("#FF8100"));
		    						CanvasDraw.printInfo(canvas, counts, frameRate);
								}
								else
									changeState(MachineState.StopState);
								return null;
							}
						};
					}
				};
				s.setPeriod(new Duration(1e3 / rate));
				s.start();
    			break;
    			
    		}
    		case StopState:
    		{
    			stopBtn.setDisable(false);
    			s.cancel();
    			break;
    		}
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.changeState(MachineState.InitState);
		quitBtn.setOnMouseClicked(e -> Platform.exit());
		this.Algorithm_selection.getItems().addAll(
				new QuickSort(),
				new InsertionSort(),
				new SelectionSort(),
				new ShellSort()
				);
		this.Algorithm_selection.getSelectionModel().selectFirst();
		
		this.playBtn.setOnMouseClicked(e->
		{
			changeState(MachineState.RunningState);
		});
		
		this.stopBtn.setOnMouseClicked(e -> 
		{
			changeState(MachineState.InitState);
		});
		
	}
////	
//	class canvasUpdateService extends ScheduledService<Void>
//	{
//		private Deque<SwapOperation> q;
//
//		public canvasUpdateService(Deque<SwapOperation> q) {
//			this.q = q;
//		}
//		protected Task<Void> createTask() {
//			return new Task<Void>() {
//
//				@Override
//				protected Void call() throws Exception {
//					return null;
//				}
//				
//			};
//		}
//		
//	}

	enum MachineState
	{
		InitState, RunningState, StopState
	}
}

