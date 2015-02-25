package com.xelit3.gymstatus.view.routines;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xelit3.gymstatus.model.dto.Routine;

public class TableRoutinesModel extends AbstractTableModel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Object[]> routines;
	private String[] header = {"Name", "Initial date", "Finish date"};
	
	public TableRoutinesModel() {
		routines = new ArrayList<Object[]>();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public int getRowCount() {
		return routines.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return routines.get(rowIndex)[columnIndex];
	}	

	public void addRow(Routine routine){
		String [] tmpRow = new String[header.length];
		tmpRow[0] = routine.getRoutineName();
		tmpRow[1] = routine.getInitDate().toString();
		tmpRow[2] = routine.getFinishDate().toString();
		
		routines.add(tmpRow);
		fireTableDataChanged();
	}
}
