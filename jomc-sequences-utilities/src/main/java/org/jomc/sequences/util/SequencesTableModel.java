// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2009 - 2011 The JOMC Project
 *   Copyright (C) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions
 *   are met:
 *
 *     o Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     o Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE JOMC PROJECT AND CONTRIBUTORS "AS IS"
 *   AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *   THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *   PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE JOMC PROJECT OR
 *   CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *   EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *   PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *   OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *   WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *   OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *   ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.sequences.util;

import java.beans.Beans;
import java.beans.ExceptionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.table.AbstractTableModel;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequencesException;
import org.jomc.sequences.SequencesSystemException;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Swing TableModel Java Bean for displaying and editing a system's SequenceDirectory.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.util.SequencesTableModel>}.
 *   It provides objects named {@code <JOMC Sequences Utilities>} of the following specifications:
 *
 *   <ul>
 *     <li>{@code <javax.swing.table.TableModel>} at any specification level.</li>
 *   </ul>
 *
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0-beta-3-SNAPSHOT
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public class SequencesTableModel extends AbstractTableModel implements Serializable
{
    // SECTION-START[TableModel]

    public int getRowCount()
    {
        try
        {
            return this.getSequences().size();
        }
        catch ( final SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }

        return 0;
    }

    public int getColumnCount()
    {
        return DEFAULT_COLUMN_COUNT;
    }

    @Override
    public String getColumnName( final int columnIndex )
    {
        final String columnName;

        switch ( columnIndex )
        {
            case NAME_COLUMN_INDEX:
                columnName = this.getNameColumnTitle( this.getLocale() );
                break;

            case MINIMUM_COLUMN_INDEX:
                columnName = this.getMinimumColumnTitle( this.getLocale() );
                break;

            case MAXIMUM_COLUMN_INDEX:
                columnName = this.getMaximumColumnTitle( this.getLocale() );
                break;

            case INCREMENT_COLUMN_INDEX:
                columnName = this.getIncrementColumnTitle( this.getLocale() );
                break;

            case VALUE_COLUMN_INDEX:
                columnName = this.getValueColumnTitle( this.getLocale() );
                break;

            default:
                columnName = super.getColumnName( columnIndex );
                this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                break;

        }

        return columnName;
    }

    @Override
    public Class<?> getColumnClass( final int columnIndex )
    {
        final Class<?> columnClass;

        switch ( columnIndex )
        {
            case NAME_COLUMN_INDEX:
                columnClass = String.class;
                break;

            case MINIMUM_COLUMN_INDEX:
            case MAXIMUM_COLUMN_INDEX:
            case INCREMENT_COLUMN_INDEX:
            case VALUE_COLUMN_INDEX:
                columnClass = BigInteger.class;
                break;

            default:
                columnClass = super.getColumnClass( columnIndex );
                this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                break;

        }

        return columnClass;
    }

    @Override
    public boolean isCellEditable( final int rowIndex, final int columnIndex )
    {
        final boolean cellEditable;

        switch ( columnIndex )
        {
            case NAME_COLUMN_INDEX:
                cellEditable = this.getNameColumnEditable();
                break;

            case MINIMUM_COLUMN_INDEX:
                cellEditable = this.getMinimumColumnEditable();
                break;

            case MAXIMUM_COLUMN_INDEX:
                cellEditable = this.getMaximumColumnEditable();
                break;

            case INCREMENT_COLUMN_INDEX:
                cellEditable = this.getIncrementColumnEditable();
                break;

            case VALUE_COLUMN_INDEX:
                cellEditable = this.getValueColumnEditable();
                break;

            default:
                cellEditable = super.isCellEditable( rowIndex, columnIndex );
                this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                break;

        }

        return cellEditable;
    }

    public Object getValueAt( final int rowIndex, final int columnIndex )
    {
        try
        {
            final Object value;
            final Sequence sequence = this.getSequences().get( rowIndex );

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    value = sequence.getName();
                    break;

                case MINIMUM_COLUMN_INDEX:
                    value = sequence.getMinimum();
                    break;

                case MAXIMUM_COLUMN_INDEX:
                    value = sequence.getMaximum();
                    break;

                case INCREMENT_COLUMN_INDEX:
                    value = sequence.getIncrement();
                    break;

                case VALUE_COLUMN_INDEX:
                    value = sequence.getValue();
                    break;

                default:
                    value = null;
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            return value;
        }
        catch ( final SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
        }
        catch ( final IndexOutOfBoundsException e )
        {
            this.fireExceptionThrown( e );
        }

        return null;
    }

    @Override
    public void setValueAt( final Object aValue, final int rowIndex, final int columnIndex )
    {
        try
        {
            final Sequence sequence = this.getSequences().get( rowIndex );
            final String name = sequence.getName();
            final long revision = sequence.getRevision();

            switch ( columnIndex )
            {
                case NAME_COLUMN_INDEX:
                    sequence.setName( aValue.toString() );
                    break;

                case MINIMUM_COLUMN_INDEX:
                    sequence.setMinimum( (Long) aValue );
                    break;

                case MAXIMUM_COLUMN_INDEX:
                    sequence.setMaximum( (Long) aValue );
                    break;

                case INCREMENT_COLUMN_INDEX:
                    sequence.setIncrement( (Long) aValue );
                    break;

                case VALUE_COLUMN_INDEX:
                    sequence.setValue( (Long) aValue );
                    break;

                default:
                    this.getLogger().warn( this.getIllegalColumnIndexMessage( this.getLocale(), columnIndex ) );
                    break;

            }

            if ( !Beans.isDesignTime() )
            {
                this.getSequenceDirectory().editSequence( name, revision, sequence );
            }

            this.fireTableRowsUpdated( rowIndex, rowIndex );
        }
        catch ( final SequencesException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
        catch ( final SequencesSystemException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
        catch ( final IndexOutOfBoundsException e )
        {
            this.fireExceptionThrown( e );
            this.sequences = null;
            this.fireTableDataChanged();
        }
    }

    // SECTION-END
    // SECTION-START[SequencesTableModel]
    /** Index of the column displaying a sequence's name. */
    public static final int NAME_COLUMN_INDEX = 0;

    /** Index of the column displaying a sequence's minimum value. */
    public static final int MINIMUM_COLUMN_INDEX = 1;

    /** Index of the column displaying a sequence's maximum value. */
    public static final int MAXIMUM_COLUMN_INDEX = 2;

    /** Index of the column displaying a sequence's increment value. */
    public static final int INCREMENT_COLUMN_INDEX = 3;

    /** Index of the column displaying a sequence's value. */
    public static final int VALUE_COLUMN_INDEX = 4;

    /** Name of property {@code nameColumnEditable}. */
    public static final String NAME_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.NAME_COLUMN_EDITABLE";

    /** Name of property {@code minimumColumnEditable}. */
    public static final String MINIMUM_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.MINIMUM_COLUMN_EDITABLE";

    /** Name of property {@code maximumColumnEditable}. */
    public static final String MAXIMUM_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.MAXIMUM_COLUMN_EDITABLE";

    /** Name of property {@code incrementColumnEditable}. */
    public static final String INCREMENT_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.INCREMENT_COLUMN_EDITABLE";

    /** Name of property {@code valueColumnEditable}. */
    public static final String VALUE_COLUMN_EDITABLE =
        "org.jomc.sequences.util.SequencesTableModel.VALUE_COLUMN_EDITABLE";

    /** Name of property {@code sequenceFilter}. */
    public static final String SEQUENCE_FILTER =
        "org.jomc.sequences.util.SequencesTableModel.SEQUENCE_FILTER";

    /** Default number of table columns. */
    private static final int DEFAULT_COLUMN_COUNT = 5;

    /**
     * Flag indicating that the {@code name} column is editable.
     * @serial
     */
    private Boolean nameColumnEditable;

    /**
     * Flag indicating that the {@code minimum} column is editable.
     * @serial
     */
    private Boolean minimumColumnEditable;

    /**
     * Flag indicating that the {@code maximum} column is editable.
     * @serial
     */
    private Boolean maximumColumnEditable;

    /**
     * Flag indicating that the {@code increment} column is editable.
     * @serial
     */
    private Boolean incrementColumnEditable;

    /**
     * Flag indicating that the {@code value} column is editable.
     * @serial
     */
    private Boolean valueColumnEditable;

    /**
     * Entity filter.
     * @serial
     */
    private Sequence sequenceFilter;

    /** Sequences of the model. */
    private transient List<Sequence> sequences;

    /**
     * Change support.
     * @serial
     */
    private PropertyChangeSupport changeSupport = new SwingPropertyChangeSupport( this );

    /**
     * Gets the flag indicating that the {@code name} column is editable.
     *
     * @return {@code true} if the {@code name} column is editable; {@code false} if not.
     */
    public final Boolean getNameColumnEditable()
    {
        if ( this.nameColumnEditable == null )
        {
            this.nameColumnEditable = this.isNameColumnEditableByDefault();
            this.changeSupport.firePropertyChange( NAME_COLUMN_EDITABLE, null, this.nameColumnEditable );
        }

        return this.nameColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code name} column is editable.
     *
     * @param value {@code true} if the {@code name} column should be editable; {@code false} if not.
     */
    public final void setNameColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.nameColumnEditable;
        this.nameColumnEditable = value;
        this.changeSupport.firePropertyChange( NAME_COLUMN_EDITABLE, oldValue, this.nameColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code minimum} column is editable.
     *
     * @return {@code true} if the {@code minimum} column is editable;{@code false} if not.
     */
    public final Boolean getMinimumColumnEditable()
    {
        if ( this.minimumColumnEditable == null )
        {
            this.minimumColumnEditable = this.isMinimumColumnEditableByDefault();
            this.changeSupport.firePropertyChange( MINIMUM_COLUMN_EDITABLE, null, this.minimumColumnEditable );
        }

        return this.minimumColumnEditable;
    }

    /**
     * Set the flag indicating that the {@code minimum} column is editable.
     *
     * @param value {@code true} if the {@code minimum} column should be editable; {@code false} if not.
     */
    public final void setMinimumColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.minimumColumnEditable;
        this.minimumColumnEditable = value;
        this.changeSupport.firePropertyChange( MINIMUM_COLUMN_EDITABLE, oldValue, this.minimumColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code maximum} column is editable.
     *
     * @return {@code true} if the {@code maximum} column is editable; {@code false} if not.
     */
    public final Boolean getMaximumColumnEditable()
    {
        if ( this.maximumColumnEditable == null )
        {
            this.maximumColumnEditable = this.isMaximumColumnEditableByDefault();
            this.changeSupport.firePropertyChange( MAXIMUM_COLUMN_EDITABLE, null, this.maximumColumnEditable );
        }

        return this.maximumColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code maximum} column is editable.
     *
     * @param value {@code true} if the {@code maximum} column should be editable; {@code false} if not.
     */
    public final void setMaximumColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.maximumColumnEditable;
        this.maximumColumnEditable = value;
        this.changeSupport.firePropertyChange( MAXIMUM_COLUMN_EDITABLE, oldValue, this.maximumColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code increment} column is editable.
     *
     * @return {@code true} if the {@code increment} column is editable; {@code false} if not.
     */
    public final Boolean getIncrementColumnEditable()
    {
        if ( this.incrementColumnEditable == null )
        {
            this.incrementColumnEditable = this.isIncrementColumnEditableByDefault();
            this.changeSupport.firePropertyChange( INCREMENT_COLUMN_EDITABLE, null, this.incrementColumnEditable );
        }

        return this.incrementColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code increment} column is editable.
     *
     * @param value {@code true} if the {@code increment} column should be editable; {@code false} if not.
     */
    public final void setIncrementColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.incrementColumnEditable;
        this.incrementColumnEditable = value;
        this.changeSupport.firePropertyChange( INCREMENT_COLUMN_EDITABLE, oldValue, this.incrementColumnEditable );
    }

    /**
     * Gets the flag indicating that the {@code value} column is editable.
     *
     * @return {@code true} if the {@code value} column is editable; {@code false} if not.
     */
    public final Boolean getValueColumnEditable()
    {
        if ( this.valueColumnEditable == null )
        {
            this.valueColumnEditable = this.isValueColumnEditableByDefault();
            this.changeSupport.firePropertyChange( VALUE_COLUMN_EDITABLE, null, this.valueColumnEditable );
        }

        return this.valueColumnEditable;
    }

    /**
     * Sets the flag indicating that the {@code value} column is editable.
     *
     * @param value {@code true} if the {@code value} column should be editable; {@code false} if not.
     */
    public final void setValueColumnEditable( final Boolean value )
    {
        final Boolean oldValue = this.valueColumnEditable;
        this.valueColumnEditable = value;
        this.changeSupport.firePropertyChange( VALUE_COLUMN_EDITABLE, oldValue, this.valueColumnEditable );
    }

    /**
     * Gets the entity used for filtering sequences.
     *
     * @return Entity used for filtering sequences or {@code null}.
     */
    public final Sequence getSequenceFilter()
    {
        return this.sequenceFilter;
    }

    /**
     * Sets the entity used for filtering sequences.
     *
     * @param value Entity to use for filtering sequences or {@code null}.
     */
    public final void setSequenceFilter( final Sequence value )
    {
        final Sequence oldValue = this.sequenceFilter;
        this.sequenceFilter = value;
        this.sequences = null;
        this.fireTableDataChanged();
        this.changeSupport.firePropertyChange( SEQUENCE_FILTER, oldValue, this.sequenceFilter );
    }

    /**
     * Add a {@code PropertyChangeListener} to the listener list.
     * <p>The listener is registered for all properties. The same listener object may be added more than once, and will
     * be called as many times as it is added. If {@code listener} is {@code null}, no exception is thrown and no action
     * is taken.</p>
     *
     * @param listener The listener to be added.
     */
    public final void addPropertyChangeListener( final PropertyChangeListener listener )
    {
        this.changeSupport.addPropertyChangeListener( listener );
    }

    /**
     * Removes a {@code PropertyChangeListener} from the listener list.
     * <p>This removes a {@code PropertyChangeListener} that was registered for all properties. If {@code listener} was
     * added more than once, it will be notified one less time after being removed. If {@code listener} is {@code null},
     * or was never added, no exception is thrown and no action is taken.</p>
     *
     * @param listener The listener to be removed.
     */
    public final void removePropertyChangeListener( final PropertyChangeListener listener )
    {
        this.changeSupport.removePropertyChangeListener( listener );
    }

    /**
     * Gets an array of all the listeners that were added to the instance.
     * <p>If some listeners have been added with a named property, then the returned array will be a mixture of
     * {@code PropertyChangeListeners} and {@code PropertyChangeListenerProxy}s. If the calling method is interested in
     * distinguishing the listeners then it must test each element to see if it's a {@code PropertyChangeListenerProxy},
     * perform the cast, and examine the parameter.</p>
     *
     * @return All of the {@code PropertyChangeListeners} added or an empty array if no listeners have been added.
     *
     * @see PropertyChangeSupport#getPropertyChangeListeners()
     */
    public final PropertyChangeListener[] getPropertyChangeListeners()
    {
        return this.changeSupport.getPropertyChangeListeners();
    }

    /**
     * Add a {@code PropertyChangeListener} for a specific property.
     * <p>The listener will be invoked only when an event for that specific property occurs. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number of times it was added for
     * that property. If {@code propertyName} or {@code listener} is {@code null}, no exception is thrown and no action
     * is taken.</p>
     *
     * @param propertyName The name of the property to listen on.
     * @param listener The listener to be added.
     */
    public final void addPropertyChangeListener( final String propertyName, final PropertyChangeListener listener )
    {
        this.changeSupport.addPropertyChangeListener( propertyName, listener );
    }

    /**
     * Removes a {@code PropertyChangeListener} for a specific property.
     * <p>If {@code listener} was added more than once to the instance for the specified property, it will be notified
     * one less time after being removed. If {@code propertyName} is {@code null}, no exception is thrown and no action
     * is taken. If {@code listener} is {@code null}, or was never added for the specified property, no exception is
     * thrown and no action is taken.</p>
     *
     * @param propertyName The name of the property that was listened on.
     * @param listener The listener to be removed.
     */
    public final void removePropertyChangeListener( final String propertyName, final PropertyChangeListener listener )
    {
        this.changeSupport.removePropertyChangeListener( propertyName, listener );
    }

    /**
     * Gets an array of all the listeners which have been associated with the named property.
     *
     * @param propertyName The name of the property being listened to.
     *
     * @return All of the {@code PropertyChangeListeners} associated with the named property. If no such listeners have
     * been added, or if {@code propertyName} is {@code null}, an empty array is returned.
     */
    public final PropertyChangeListener[] getPropertyChangeListeners( final String propertyName )
    {
        return this.changeSupport.getPropertyChangeListeners( propertyName );
    }

    /**
     * Gets the entities of the model.
     * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make
     * to the returned list will be present inside the object.</p>
     *
     * @return The entities of the model.
     *
     * @throws SequencesSystemException if searching entities fails.
     */
    protected List<Sequence> getSequences() throws SequencesSystemException
    {
        if ( this.sequences == null )
        {
            this.sequences = new LinkedList<Sequence>();

            if ( !Beans.isDesignTime() )
            {
                this.sequences.addAll( this.getSequenceDirectory().searchSequences(
                    this.getSequenceFilter() != null ? this.getSequenceFilter().getName() : null ) );

            }
        }

        return this.sequences;
    }

    /**
     * Notifies any available {@code ExceptionListener} whenever a recoverable exception has been caught.
     *
     * @param e The exception that was caught.
     */
    protected void fireExceptionThrown( final Exception e )
    {
        this.getLogger().error( e );

        if ( this.getExceptionListener() != null )
        {
            for ( ExceptionListener l : this.getExceptionListener() )
            {
                l.exceptionThrown( e );
            }
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code SequencesTableModel} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public SequencesTableModel()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // <editor-fold defaultstate="collapsed" desc=" Generated Dependencies ">

    /**
     * Gets the {@code <ExceptionListener>} dependency.
     * <p>
     *   This method returns any available object of the {@code <JOMC :: Sequences :: ExceptionListener>} specification at specification level 1.0.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <ExceptionListener>} dependency.
     * {@code null} if no object is available.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.beans.ExceptionListener[] getExceptionListener()
    {
        return (java.beans.ExceptionListener[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "ExceptionListener" );
    }

    /**
     * Gets the {@code <Locale>} dependency.
     * <p>
     *   This method returns the {@code <default>} object of the {@code <java.util.Locale>} specification at specification level 1.1.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <Locale>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.util.Locale getLocale()
    {
        final java.util.Locale _d = (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "Locale" );
        assert _d != null : "'Locale' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <Logger>} dependency.
     * <p>
     *   This method returns any available object of the {@code <org.jomc.logging.Logger>} specification at specification level 1.0.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * <p><strong>Properties:</strong>
     *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
     *     <tr class="TableSubHeadingColor">
     *       <th align="left" scope="col" nowrap><b>Name</b></th>
     *       <th align="left" scope="col" nowrap><b>Type</b></th>
     *       <th align="left" scope="col" nowrap><b>Documentation</b></th>
     *     </tr>
     *     <tr class="TableRow">
     *       <td align="left" valign="top" nowrap>{@code <name>}</td>
     *       <td align="left" valign="top" nowrap>{@code java.lang.String}</td>
     *       <td align="left" valign="top"></td>
     *     </tr>
     *   </table>
     * </p>
     * @return The {@code <Logger>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.logging.Logger getLogger()
    {
        final org.jomc.logging.Logger _d = (org.jomc.logging.Logger) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "Logger" );
        assert _d != null : "'Logger' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SequenceDirectory>} dependency.
     * <p>
     *   This method returns any available object of the {@code <org.jomc.sequences.SequenceDirectory>} specification at specification level 1.0.
     *   That specification applies to {@code <Singleton>} scope. The singleton object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <SequenceDirectory>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.sequences.SequenceDirectory getSequenceDirectory()
    {
        final org.jomc.sequences.SequenceDirectory _d = (org.jomc.sequences.SequenceDirectory) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceDirectory" );
        assert _d != null : "'SequenceDirectory' dependency not found.";
        return _d;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // <editor-fold defaultstate="collapsed" desc=" Generated Properties ">

    /**
     * Gets the value of the {@code <incrementColumnEditableByDefault>} property.
     * @return Flag indicating that the &quot;increment&quot; column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.Boolean isIncrementColumnEditableByDefault()
    {
        final java.lang.Boolean _p = (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "incrementColumnEditableByDefault" );
        assert _p != null : "'incrementColumnEditableByDefault' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <maximumColumnEditableByDefault>} property.
     * @return Flag indicating that the &quot;maximum&quot; column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.Boolean isMaximumColumnEditableByDefault()
    {
        final java.lang.Boolean _p = (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "maximumColumnEditableByDefault" );
        assert _p != null : "'maximumColumnEditableByDefault' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <minimumColumnEditableByDefault>} property.
     * @return Flag indicating that the &quot;minimum&quot; column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.Boolean isMinimumColumnEditableByDefault()
    {
        final java.lang.Boolean _p = (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "minimumColumnEditableByDefault" );
        assert _p != null : "'minimumColumnEditableByDefault' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <nameColumnEditableByDefault>} property.
     * @return Flag indicating that the &quot;name&quot; column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.Boolean isNameColumnEditableByDefault()
    {
        final java.lang.Boolean _p = (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "nameColumnEditableByDefault" );
        assert _p != null : "'nameColumnEditableByDefault' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <valueColumnEditableByDefault>} property.
     * @return Flag indicating that the &quot;value&quot; column is editable by default.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.Boolean isValueColumnEditableByDefault()
    {
        final java.lang.Boolean _p = (java.lang.Boolean) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "valueColumnEditableByDefault" );
        assert _p != null : "'valueColumnEditableByDefault' property not found.";
        return _p;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Messages]
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">

    /**
     * Gets the text of the {@code <illegalColumnIndexMessage>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param columnIndex Format argument.
     * @return The text of the {@code <illegalColumnIndexMessage>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getIllegalColumnIndexMessage( final java.util.Locale locale, final java.lang.Number columnIndex )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "illegalColumnIndexMessage", locale, columnIndex );
        assert _m != null : "'illegalColumnIndexMessage' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <incrementColumnTitle>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code <incrementColumnTitle>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getIncrementColumnTitle( final java.util.Locale locale )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "incrementColumnTitle", locale );
        assert _m != null : "'incrementColumnTitle' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <maximumColumnTitle>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code <maximumColumnTitle>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getMaximumColumnTitle( final java.util.Locale locale )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "maximumColumnTitle", locale );
        assert _m != null : "'maximumColumnTitle' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <minimumColumnTitle>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code <minimumColumnTitle>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getMinimumColumnTitle( final java.util.Locale locale )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "minimumColumnTitle", locale );
        assert _m != null : "'minimumColumnTitle' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <nameColumnTitle>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code <nameColumnTitle>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getNameColumnTitle( final java.util.Locale locale )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "nameColumnTitle", locale );
        assert _m != null : "'nameColumnTitle' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <valueColumnTitle>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @return The text of the {@code <valueColumnTitle>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getValueColumnTitle( final java.util.Locale locale )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "valueColumnTitle", locale );
        assert _m != null : "'valueColumnTitle' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
