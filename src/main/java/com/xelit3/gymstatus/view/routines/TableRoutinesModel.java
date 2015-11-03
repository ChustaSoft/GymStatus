package com.xelit3.gymstatus.view.routines;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.xelit3.gymstatus.model.dto.Routine;

// TODO: Auto-generated Javadoc
/**
 * The Class TableRoutinesModel.
 */
public class TableRoutinesModel extends AbstractTableModel  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The routines. */
	private List<Object[]> routines = new ArrayList<Object[]>();
	
	/** The header. */
	private String[] header = {"Name", "Initial date", "Finish date"};
	
	/**
	 * Instantiates a new table routines model.
	 */
	public TableRoutinesModel() {
		super();		
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return header.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return routines.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return routines.get(rowIndex)[columnIndex];
	}	

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int arg0) {
		return header[arg0];
	}
	
	/**
	 * Adds the row.
	 *
	 * @param routine the routine
	 */
	public void addRow(Routine routine){
		String [] tmpRow = new String[header.length];
		tmpRow[0] = routine.getRoutineName();
		tmpRow[1] = routine.getInitDate().toString();
		tmpRow[2] = routine.getFinishDate().toString();
		
		routines.add(tmpRow);
		fireTableDataChanged();
	}
	
}
