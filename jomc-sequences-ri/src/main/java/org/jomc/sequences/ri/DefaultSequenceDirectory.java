// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) Christian Schulte, 2005-206
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
 *   THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 *   INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 *   AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 *   THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT, INDIRECT,
 *   INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *   NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *   DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *   THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *   THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *   $JOMC$
 *
 */
// </editor-fold>
// SECTION-END
package org.jomc.sequences.ri;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.jomc.sequences.CapacityLimitException;
import org.jomc.sequences.ConcurrentModificationException;
import org.jomc.sequences.SequenceExistsException;
import org.jomc.sequences.SequenceVetoException;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;
import org.jomc.sequences.SequenceChangeListener;
import org.jomc.sequences.SequenceDirectory;
import org.jomc.sequences.SequenceLimitException;
import org.jomc.sequences.SequenceNotFoundException;
import org.jomc.sequences.SequenceOperations;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.VetoableSequenceChangeListener;
import org.jomc.sequences.model.SequenceType;
import org.jomc.sequences.model.SequencesType;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * SequenceDirectory reference implementation.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.ri.DefaultSequenceDirectory>}.
 *   It provides objects named {@code <JOMC Sequences RI>} of the following specifications:
 *
 *   <ul>
 *     <li>{@code <org.jomc.sequences.SequenceDirectory>} at specification level 1.0 applying to {@code <Singleton>} scope.</li>
 *     <li>{@code <org.jomc.sequences.SequenceOperations>} at specification level 1.0 applying to {@code <Singleton>} scope.</li>
 *   </ul>
 *
 *   No state is retained across operations due to flag {@code <stateless>}.
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
public class DefaultSequenceDirectory
    implements SequenceDirectory, SequenceOperations
{
    // SECTION-START[SequenceDirectory]

    public BigInteger getSequenceCount()
    {
        final Query query = this.getSelectSequenceCountQuery();
        query.setParameter( this.getSequenceDirectoryNameQueryParameterName(),
                            this.getSequencesType( this.getSequenceDirectoryName() ).getName() );

        return BigInteger.valueOf( ( (Long) query.getSingleResult() ).longValue() );
    }

    public BigInteger getCapacityLimit()
    {
        return this.getSequencesType( this.getSequenceDirectoryName() ).getCapacityLimit();
    }

    public Sequence getSequence( final String name )
    {
        if ( name == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "name", null ) );
        }

        final SequenceType sequenceType = this.getSequenceType( name );
        if ( sequenceType != null )
        {
            return this.getSequenceMapper().map( sequenceType, new Sequence() );
        }

        return null;
    }

    public Sequence addSequence( final Sequence sequence )
    {
        if ( sequence == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "sequence", null ) );
        }

        final BigInteger capacityLimit = this.getCapacityLimit();
        if ( this.getSequenceCount().compareTo( capacityLimit ) >= 0 )
        {
            throw new CapacityLimitException( capacityLimit );
        }

        final SequenceChangeEvent sequenceChange = new SequenceChangeEvent( this, null, sequence );
        this.fireVetoableSequenceChange( sequenceChange );

        SequenceType sequenceType = this.getSequenceType( sequence.getName() );

        if ( sequenceType != null )
        {
            throw new SequenceExistsException( this.getSequenceMapper().map( sequenceType, new Sequence() ) );
        }

        sequenceType = this.getSequenceMapper().map( sequence, new SequenceType() );
        sequenceType.setJpaDate( Calendar.getInstance() );

        final SequencesType sequences = this.getSequencesType( this.getSequenceDirectoryName() );
        this.getEntityManager().persist( sequenceType );
        sequences.getSequence().add( sequenceType );
        this.getEntityManager().merge( sequences );

        final Sequence persistent = this.getSequenceMapper().map( sequenceType, new Sequence() );
        final SequenceChangeEvent sequenceChanged = new SequenceChangeEvent( this, null, persistent );

        for ( String k : sequenceChange.getStatusKeys() )
        {
            sequenceChanged.getStatus( k ).addAll( sequenceChange.getStatus( k ) );
        }

        this.fireSequenceChange( sequenceChanged );
        return persistent;
    }

    public Sequence editSequence( final String name, final long revision, final Sequence sequence )
    {
        if ( name == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "name", null ) );
        }
        if ( sequence == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "sequence", null ) );
        }

        SequenceType sequenceType = this.getSequenceType( name );

        if ( sequenceType == null )
        {
            throw new SequenceNotFoundException( name );
        }
        if ( sequenceType.getRevision() != revision )
        {
            throw new ConcurrentModificationException( this.getSequenceMapper().map( sequenceType, new Sequence() ) );
        }

        final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );
        final SequenceChangeEvent sequenceChange = new SequenceChangeEvent( this, oldValue, sequence );
        this.fireVetoableSequenceChange( sequenceChange );

        sequenceType = this.getSequenceMapper().map( sequence, sequenceType );
        sequenceType.setRevision( sequenceType.getRevision() + 1L );
        sequenceType.setJpaDate( Calendar.getInstance() );
        this.getEntityManager().merge( sequenceType );

        final Sequence edited = this.getSequenceMapper().map( sequenceType, new Sequence() );
        final SequenceChangeEvent sequenceChanged = new SequenceChangeEvent( this, oldValue, edited );

        for ( String k : sequenceChange.getStatusKeys() )
        {
            sequenceChanged.getStatus( k ).addAll( sequenceChange.getStatus( k ) );
        }

        this.fireSequenceChange( sequenceChanged );
        return edited;
    }

    public Sequence deleteSequence( final String name, final long revision )
    {
        if ( name == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "name", null ) );
        }

        final SequenceType sequenceType = this.getSequenceType( name );

        if ( sequenceType == null )
        {
            throw new SequenceNotFoundException( name );
        }
        if ( sequenceType.getRevision() != revision )
        {
            throw new ConcurrentModificationException( this.getSequenceMapper().map( sequenceType, new Sequence() ) );
        }

        final Sequence deleted = this.getSequenceMapper().map( sequenceType, new Sequence() );
        final SequenceChangeEvent sequenceChange = new SequenceChangeEvent( this, deleted, null );
        this.fireVetoableSequenceChange( sequenceChange );

        final SequencesType sequences = this.getSequencesType( this.getSequenceDirectoryName() );
        sequences.getSequence().remove( sequenceType );
        this.getEntityManager().remove( sequenceType );

        if ( sequences.getSequence().isEmpty() )
        {
            this.getEntityManager().remove( sequences );
        }
        else
        {
            this.getEntityManager().merge( sequences );
        }

        final Sequence s = this.getSequenceMapper().map( sequenceType, new Sequence() );
        final SequenceChangeEvent sequenceChanged = new SequenceChangeEvent( this, s, null );

        for ( String k : sequenceChange.getStatusKeys() )
        {
            sequenceChanged.getStatus( k ).addAll( sequenceChange.getStatus( k ) );
        }

        this.fireSequenceChange( sequenceChanged );
        return deleted;
    }

    public Set<Sequence> searchSequences( final String name )
    {
        final Query query = name == null ? this.getSelectAllSequencesQuery() : this.getSelectSequencesQuery();
        query.setParameter( this.getSequenceDirectoryNameQueryParameterName(),
                            this.getSequencesType( this.getSequenceDirectoryName() ).getName() );

        if ( name != null )
        {
            query.setParameter( this.getSequenceNameQueryParameterName(), name );
        }

        final List<?> resultList = query.getResultList();
        final Set<Sequence> sequences = new HashSet<Sequence>( resultList.size() );

        for ( Object o : resultList )
        {
            if ( o == null )
            {
                throw new SequencesSystemException( getMissingResultObjectError(
                    this.getLocale(), this.getSequenceDirectoryName() ) );

            }

            if ( !( o instanceof SequenceType ) )
            {
                throw new SequencesSystemException( getIllegalResultObjectError(
                    this.getLocale(), this.getSequenceDirectoryName(), o.toString() ) );

            }

            sequences.add( this.getSequenceMapper().map( (SequenceType) o, new Sequence() ) );
        }

        return sequences;
    }

    // SECTION-END
    // SECTION-START[SequenceOperations]
    public long getNextSequenceValue( final String sequenceName )
    {
        if ( sequenceName == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage(
                this.getLocale(), "sequenceName", null ) );

        }

        final SequenceType sequenceType = this.getSequenceType( sequenceName );

        if ( sequenceType == null )
        {
            throw new SequenceNotFoundException( sequenceName );
        }

        final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );
        final Long nextValue = sequenceType.getValue() + sequenceType.getIncrement();

        if ( nextValue < sequenceType.getValue() || nextValue > sequenceType.getMaximum() )
        {
            throw new SequenceLimitException( sequenceType.getValue() );
        }

        sequenceType.setValue( nextValue );
        sequenceType.setRevision( sequenceType.getRevision() + 1L );
        sequenceType.setJpaDate( Calendar.getInstance() );

        this.getEntityManager().merge( sequenceType );

        final Sequence s = this.getSequenceMapper().map( sequenceType, new Sequence() );
        this.fireSequenceChange( new SequenceChangeEvent( this, oldValue, s ) );
        return s.getValue();
    }

    public long[] getNextSequenceValues( final String sequenceName, final int numValues )
    {
        if ( sequenceName == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage(
                this.getLocale(), "sequenceName", null ) );

        }
        if ( numValues < 0 )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage(
                this.getLocale(), "numValues", Integer.toString( numValues ) ) );

        }

        final SequenceType sequenceType = this.getSequenceType( sequenceName );

        if ( sequenceType == null )
        {
            throw new SequenceNotFoundException( sequenceName );
        }

        final long[] values = new long[ numValues ];
        final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );

        for ( int i = values.length - 1; i >= 0; i-- )
        {
            final long nextValue = sequenceType.getValue() + sequenceType.getIncrement();

            if ( nextValue < sequenceType.getValue() || nextValue > sequenceType.getMaximum() )
            {
                throw new SequenceLimitException( sequenceType.getValue() );
            }

            sequenceType.setValue( nextValue );
            values[i] = nextValue;
        }

        sequenceType.setRevision( sequenceType.getRevision() + 1L );
        sequenceType.setJpaDate( Calendar.getInstance() );

        this.getEntityManager().merge( sequenceType );

        final Sequence s = this.getSequenceMapper().map( sequenceType, new Sequence() );
        this.fireSequenceChange( new SequenceChangeEvent( this, oldValue, s ) );
        return values;
    }

    // SECTION-END
    // SECTION-START[DefaultSequenceDirectory]
    /**
     * Gets a sequence entity for a given name.
     *
     * @param name The name of the sequence entity to return.
     *
     * @return The sequence entity with name {@code name}, or {@code null} if no sequence entity matching {@code name}
     * is found.
     */
    protected SequenceType getSequenceType( final String name )
    {
        try
        {
            final Query query = this.getSelectSequenceQuery();
            query.setParameter( this.getSequenceDirectoryNameQueryParameterName(),
                                this.getSequencesType( this.getSequenceDirectoryName() ).getName() );

            query.setParameter( this.getSequenceNameQueryParameterName(), name );

            return (SequenceType) query.getSingleResult();
        }
        catch ( final NoResultException e )
        {
            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( getMessage( e ) );
            }

            return null;
        }
    }

    /**
     * Gets a sequence directory entity for a given name.
     *
     * @param name The name of the sequence directory entity to return.
     *
     * @return The sequence directory entity matching {@code name}; if no such sequence directory entity is found, a
     * new entity is created.
     */
    protected SequencesType getSequencesType( final String name )
    {
        final Query query = this.getSelectSequenceDirectoryQuery();
        query.setParameter( this.getSequenceDirectoryNameQueryParameterName(), name );

        SequencesType sequencesType = null;

        try
        {
            sequencesType = (SequencesType) query.getSingleResult();
        }
        catch ( final NoResultException e )
        {
            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( getMessage( e ) );
            }

            sequencesType = new SequencesType();
            sequencesType.setName( name );

            BigInteger defaultCapacityLimit = sequencesType.getCapacityLimit();
            if ( defaultCapacityLimit == null )
            {
                defaultCapacityLimit = this.getDefaultSequenceDirectoryCapacityLimit();
            }

            sequencesType.setCapacityLimit( defaultCapacityLimit );
            sequencesType.setJpaDate( Calendar.getInstance() );

            this.getEntityManager().persist( sequencesType );

            if ( this.getLogger().isInfoEnabled() )
            {
                this.getLogger().info( this.getSuccessfullyCreatedSequenceDirectoryMessage( this.getLocale(), name ) );
            }
        }

        return sequencesType;
    }

    /**
     * Notifies all available {@code SequenceChangeListener}s that a sequence changed.
     *
     * @param sequenceChange The event to notify listeners about.
     *
     * @throws NullPointerException if {@code sequenceChange} is {@code null}.
     */
    protected void fireSequenceChange( final SequenceChangeEvent sequenceChange )
    {
        if ( sequenceChange == null )
        {
            throw new NullPointerException( "sequenceChange" );
        }

        for ( SequenceChangeListener l : this.getSequenceChangeListener() )
        {
            l.sequenceChange( sequenceChange );
        }
    }

    /**
     * Notifies all available {@code SequenceChangeListener}s that a sequence is about to change.
     *
     * @param sequenceChange The event to notify listeners about.
     *
     * @throws NullPointerException if {@code sequenceChange} is {@code null}.
     * @throws SequenceVetoException if any available {@code SequenceChangeListener} chooses to veto the sequence
     * change.
     */
    protected void fireVetoableSequenceChange( final SequenceChangeEvent sequenceChange )
    {
        if ( sequenceChange == null )
        {
            throw new NullPointerException( "sequenceChange" );
        }

        boolean vetoed = false;

        for ( VetoableSequenceChangeListener l : this.getVetoableSequenceChangeListener() )
        {
            try
            {
                l.vetoableSequenceChange( sequenceChange );
            }
            catch ( final SequenceVetoException e )
            {
                this.getLogger().error( getMessage( e ) );
                vetoed = true;
            }
        }

        if ( vetoed )
        {
            throw new SequenceVetoException( sequenceChange );
        }
    }

    private static String getMessage( final Throwable t )
    {
        return t != null ? t.getMessage() != null ? t.getMessage() : getMessage( t.getCause() ) : null;
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultSequenceDirectory} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public DefaultSequenceDirectory()
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
     * Gets the {@code <EntityManager>} dependency.
     * <p>
     *   This method returns the {@code <JOMC SDK JPA>} object of the {@code <javax.persistence.EntityManager>} specification at any specification level.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * @return The {@code <EntityManager>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private javax.persistence.EntityManager getEntityManager()
    {
        final javax.persistence.EntityManager _d = (javax.persistence.EntityManager) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "EntityManager" );
        assert _d != null : "'EntityManager' dependency not found.";
        return _d;
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
     * Gets the {@code <SelectAllSequencesQuery>} dependency.
     * <p>
     *   This method returns the {@code <JOMC Sequences Model :: Select All Sequences Query>} object of the {@code <javax.persistence.Query>} specification at any specification level.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * @return The {@code <SelectAllSequencesQuery>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private javax.persistence.Query getSelectAllSequencesQuery()
    {
        final javax.persistence.Query _d = (javax.persistence.Query) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectAllSequencesQuery" );
        assert _d != null : "'SelectAllSequencesQuery' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SelectSequenceCountQuery>} dependency.
     * <p>
     *   This method returns the {@code <JOMC Sequences Model :: Select Sequence Count Query>} object of the {@code <javax.persistence.Query>} specification at any specification level.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * @return The {@code <SelectSequenceCountQuery>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private javax.persistence.Query getSelectSequenceCountQuery()
    {
        final javax.persistence.Query _d = (javax.persistence.Query) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectSequenceCountQuery" );
        assert _d != null : "'SelectSequenceCountQuery' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SelectSequenceDirectoryQuery>} dependency.
     * <p>
     *   This method returns the {@code <JOMC Sequences Model :: Select Sequence Directory Query>} object of the {@code <javax.persistence.Query>} specification at any specification level.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * @return The {@code <SelectSequenceDirectoryQuery>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private javax.persistence.Query getSelectSequenceDirectoryQuery()
    {
        final javax.persistence.Query _d = (javax.persistence.Query) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectSequenceDirectoryQuery" );
        assert _d != null : "'SelectSequenceDirectoryQuery' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SelectSequenceQuery>} dependency.
     * <p>
     *   This method returns the {@code <JOMC Sequences Model :: Select Sequence Query>} object of the {@code <javax.persistence.Query>} specification at any specification level.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * @return The {@code <SelectSequenceQuery>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private javax.persistence.Query getSelectSequenceQuery()
    {
        final javax.persistence.Query _d = (javax.persistence.Query) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectSequenceQuery" );
        assert _d != null : "'SelectSequenceQuery' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SelectSequencesQuery>} dependency.
     * <p>
     *   This method returns the {@code <JOMC Sequences Model :: Select Sequences Query>} object of the {@code <javax.persistence.Query>} specification at any specification level.
     *   That specification does not apply to any scope. A new object is returned whenever requested.
     * </p>
     * @return The {@code <SelectSequencesQuery>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private javax.persistence.Query getSelectSequencesQuery()
    {
        final javax.persistence.Query _d = (javax.persistence.Query) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SelectSequencesQuery" );
        assert _d != null : "'SelectSequencesQuery' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SequenceChangeListener>} dependency.
     * <p>
     *   This method returns any available object of the {@code <org.jomc.sequences.SequenceChangeListener>} specification at specification level 1.0.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <SequenceChangeListener>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.sequences.SequenceChangeListener[] getSequenceChangeListener()
    {
        final org.jomc.sequences.SequenceChangeListener[] _d = (org.jomc.sequences.SequenceChangeListener[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceChangeListener" );
        assert _d != null : "'SequenceChangeListener' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <SequenceMapper>} dependency.
     * <p>
     *   This method returns the {@code <JOMC Sequences RI>} object of the {@code <org.jomc.sequences.ri.SequenceMapper>} specification at specification level 1.0.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <SequenceMapper>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.sequences.ri.SequenceMapper getSequenceMapper()
    {
        final org.jomc.sequences.ri.SequenceMapper _d = (org.jomc.sequences.ri.SequenceMapper) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceMapper" );
        assert _d != null : "'SequenceMapper' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code <VetoableSequenceChangeListener>} dependency.
     * <p>
     *   This method returns any available object of the {@code <org.jomc.sequences.VetoableSequenceChangeListener>} specification at specification level 1.0.
     *   That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.
     * </p>
     * @return The {@code <VetoableSequenceChangeListener>} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private org.jomc.sequences.VetoableSequenceChangeListener[] getVetoableSequenceChangeListener()
    {
        final org.jomc.sequences.VetoableSequenceChangeListener[] _d = (org.jomc.sequences.VetoableSequenceChangeListener[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "VetoableSequenceChangeListener" );
        assert _d != null : "'VetoableSequenceChangeListener' dependency not found.";
        return _d;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // <editor-fold defaultstate="collapsed" desc=" Generated Properties ">

    /**
     * Gets the value of the {@code <defaultSequenceDirectoryCapacityLimit>} property.
     * @return Default capacity limit when creating new sequence directory entities and no default value is provided otherwise.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.math.BigInteger getDefaultSequenceDirectoryCapacityLimit()
    {
        final java.math.BigInteger _p = (java.math.BigInteger) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "defaultSequenceDirectoryCapacityLimit" );
        assert _p != null : "'defaultSequenceDirectoryCapacityLimit' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <sequenceDirectoryName>} property.
     * @return Name uniquely identifying the directory in a set of directories.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.String getSequenceDirectoryName()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceDirectoryName" );
        assert _p != null : "'sequenceDirectoryName' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <sequenceDirectoryNameQueryParameterName>} property.
     * @return Name of a JPA query parameter denoting the name of a sequence directory entity.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.String getSequenceDirectoryNameQueryParameterName()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceDirectoryNameQueryParameterName" );
        assert _p != null : "'sequenceDirectoryNameQueryParameterName' property not found.";
        return _p;
    }

    /**
     * Gets the value of the {@code <sequenceNameQueryParameterName>} property.
     * @return Name of a JPA query parameter denoting the name of a sequence entity.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private java.lang.String getSequenceNameQueryParameterName()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceNameQueryParameterName" );
        assert _p != null : "'sequenceNameQueryParameterName' property not found.";
        return _p;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Messages]
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">

    /**
     * Gets the text of the {@code <illegalArgumentMessage>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param argumentName Format argument.
     * @param argumentValue Format argument.
     * @return The text of the {@code <illegalArgumentMessage>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getIllegalArgumentMessage( final java.util.Locale locale, final java.lang.String argumentName, final java.lang.String argumentValue )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "illegalArgumentMessage", locale, argumentName, argumentValue );
        assert _m != null : "'illegalArgumentMessage' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <illegalResultObjectError>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param directoryInfo Format argument.
     * @param objectInfo Format argument.
     * @return The text of the {@code <illegalResultObjectError>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getIllegalResultObjectError( final java.util.Locale locale, final java.lang.String directoryInfo, final java.lang.String objectInfo )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "illegalResultObjectError", locale, directoryInfo, objectInfo );
        assert _m != null : "'illegalResultObjectError' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <missingResultObjectError>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param directoryInfo Format argument.
     * @return The text of the {@code <missingResultObjectError>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getMissingResultObjectError( final java.util.Locale locale, final java.lang.String directoryInfo )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "missingResultObjectError", locale, directoryInfo );
        assert _m != null : "'missingResultObjectError' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code <successfullyCreatedSequenceDirectoryMessage>} message.
     * <p><strong>Languages:</strong>
     *   <ul>
     *     <li>English (default)</li>
     *     <li>Deutsch</li>
     *   </ul>
     * </p>
     *
     * @param locale The locale of the message to return.
     * @param name Format argument.
     * @return The text of the {@code <successfullyCreatedSequenceDirectoryMessage>} message for {@code locale}.
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private String getSuccessfullyCreatedSequenceDirectoryMessage( final java.util.Locale locale, final java.lang.String name )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "successfullyCreatedSequenceDirectoryMessage", locale, name );
        assert _m != null : "'successfullyCreatedSequenceDirectoryMessage' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
