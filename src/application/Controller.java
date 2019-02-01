package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import entity.AlgorithmType;
import entity.MonitoredList;
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
import sortAlgorithm.SortAlgorithmFactory;
import swapUilitity.SwapOperation;
import swapUilitity.SwapOperationQueue;
import ui.CanvasDraw;

public class Controller implements Initializable {

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

    @FXML
    private Button stopBtn;

    private canvasUpdateService s = null;
    
    private List<Integer> backupList = null;
    private MonitoredList mlist = null;
    private int counts = 0;
	private long sysDate = System.currentTimeMillis();
    
    public void generateList()
    {
    	mlist = new MonitoredList();
		backupList = new ArrayList<>();
		Random rd = new Random(System.currentTimeMillis());
		final int size = 100;
		// 将1~size加入数组
		for(int i = 0;i < size;i++)
			mlist.add(i+1);
		// 打乱顺序
		for(int i=size;i>0;i--)
			mlist.Swap(rd.nextInt(i), i-1);
		for(int i=0;i<size;i++)
			backupList.add(mlist.get(i));
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
    			CanvasDraw.associateWithList(canvas, backupList);
    			CanvasDraw.draw(this.canvas, backupList);
    			playBtn.setDisable(false);
    			Algorithm_selection.setDisable(false);
    			rateInput.setDisable(false);
    			stopBtn.setDisable(false);
				SwapOperationQueue.clear();
    			break;
    		}
    		case RunningState:
    		{
    			int rate = 60;
    			/* 获取刷新率  */
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
    			/* 关闭按键响应 */
    			playBtn.setDisable(true);
    			rateInput.setDisable(true);
    			
    			SortAlgorithmFactory.assemble(this.Algorithm_selection.getSelectionModel().getSelectedItem()).Sort(mlist);
    			
    			s = new canvasUpdateService(SwapOperationQueue.getIterator());
    			s.setPeriod(new Duration(1000.0 / rate));
    			s.setOnSucceeded(e2 -> {
    				SwapOperation swo = (SwapOperation) e2.getSource().getValue();
    				if(swo != null)
    				{
    						counts++;
    						long curDate = System.currentTimeMillis();
    						int frameRate = (int) (1000/(curDate - sysDate));
    						sysDate = curDate;
    						
    						backupList.set(swo.previousIndex, swo.previousNumber);
    						backupList.set(swo.afterIndex, swo.afterNumber);
    						CanvasDraw.clearCanvas(canvas);
    						CanvasDraw.draw(canvas, backupList);
    						CanvasDraw.drawColumn(canvas, swo.previousIndex, swo.previousNumber, Color.valueOf("#FF8100"));
    						CanvasDraw.drawColumn(canvas, swo.afterIndex, swo.afterNumber, Color.valueOf("#FF8100"));
    						CanvasDraw.printInfo(canvas, counts, frameRate);
    				}
    			});
    			s.start();
    			break;
    			
    		}
    		case StopState:
    		{
    			s.cancel();
    			break;
    		}
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.changeState(MachineState.InitState);
		quickBtn.setOnMouseClicked(e -> Platform.exit());
		this.Algorithm_selection.getItems().addAll(
			AlgorithmType.SelectionSort,
			AlgorithmType.BubbleSort,
			AlgorithmType.QuickSort
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
	
	class canvasUpdateService extends ScheduledService<SwapOperation>
	{
		private Iterator<SwapOperation> it = null;
		public canvasUpdateService(Iterator<SwapOperation> it)
		{
			this.it = it;
		}
		
		@Override
		protected Task<SwapOperation> createTask() {
			return new Task<SwapOperation>() {

				@Override
				protected SwapOperation call() throws Exception {
					if(it.hasNext())
							return it.next();
					changeState(MachineState.StopState);
					return null;
				}
				
			};
		}
			
		}
		
	}

	enum MachineState
	{
		InitState, RunningState, StopState
	}

