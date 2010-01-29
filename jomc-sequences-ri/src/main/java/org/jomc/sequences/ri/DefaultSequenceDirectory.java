// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <cs@jomc.org>
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
 *   $Id$
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
import org.jomc.sequences.DuplicateSequenceException;
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
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.SequenceDirectory} {@code 1.0} {@code Singleton}</li>
 * <li>{@code org.jomc.sequences.SequenceOperations} {@code 1.0} {@code Singleton}</li>
 * </ul></p>
 * <p><b>Properties</b><ul>
 * <li>"{@link #getDirectoryName directoryName}"
 * <blockquote>Property of type {@code java.lang.String}.
 * <p>Name uniquely identifying the directory in a set of directories.</p>
 * </blockquote></li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getEntityManager EntityManager}"<blockquote>
 * Dependency on {@code javax.persistence.EntityManager}.</blockquote></li>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 bound to an instance.</blockquote></li>
 * <li>"{@link #getLogger Logger}"<blockquote>
 * Dependency on {@code org.jomc.logging.Logger} at specification level 1.0 bound to an instance.</blockquote></li>
 * <li>"{@link #getSequenceChangeListener SequenceChangeListener}"<blockquote>
 * Dependency on {@code org.jomc.sequences.SequenceChangeListener} at specification level 1.0 bound to an instance.</blockquote></li>
 * <li>"{@link #getSequenceMapper SequenceMapper}"<blockquote>
 * Dependency on {@code org.jomc.sequences.ri.SequenceMapper} at specification level 1.0 bound to an instance.</blockquote></li>
 * <li>"{@link #getVetoableSequenceChangeListener VetoableSequenceChangeListener}"<blockquote>
 * Dependency on {@code org.jomc.sequences.VetoableSequenceChangeListener} at specification level 1.0 bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getIllegalArgumentMessage illegalArgument}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal value ''{1}'' for argument ''{0}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ung&uuml;ltiger Wert ''{1}'' f&uuml;r Parameter ''{0}''.</pre></td></tr>
 * </table>
 * <li>"{@link #getSuccessfullyCreatedSequenceDirectoryMessage successfullyCreatedSequenceDirectory}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Sequence directory ''{0}'' created.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Sequenzverzeichnis ''{0}'' erstellt.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:cs@jomc.org">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class DefaultSequenceDirectory
    implements SequenceDirectory, SequenceOperations
{
    // SECTION-START[SequenceDirectory]

    public BigInteger getSequenceCount()
    {
        final Query query = this.getEntityManager().createNamedQuery( COUNT_SEQUENCES_QUERY );
        query.setParameter( 1, this.getSequencesList().getName() );
        return BigInteger.valueOf( ( (Long) query.getSingleResult() ).longValue() );
    }

    public BigInteger getCapacityLimit()
    {
        return CAPACITY_LIMIT;
    }

    public Sequence getSequence( final String name )
    {
        if ( name == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "name", null ) );
        }

        final SequenceType sequenceType = this.getSequenceByName( name );
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

        this.assertMaximumCapacityNotReached();
        this.fireVetoableSequenceChange( null, sequence );

        SequenceType sequenceType = this.getSequenceByName( sequence.getName() );

        if ( sequenceType != null )
        {
            throw new DuplicateSequenceException( this.getSequenceMapper().map( sequenceType, new Sequence() ) );
        }

        sequenceType = this.getSequenceMapper().map( sequence, new SequenceType() );
        sequenceType.setJpaDate( Calendar.getInstance() );

        final SequencesType sequences = this.getSequencesList();
        this.getEntityManager().persist( sequenceType );
        sequences.getSequence().add( sequenceType );
        this.getEntityManager().merge( sequences );

        final Sequence persistent = this.getSequenceMapper().map( sequenceType, new Sequence() );
        this.fireSequenceChange( null, persistent );
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

        SequenceType sequenceType = this.getSequenceByName( name );

        if ( sequenceType == null )
        {
            throw new SequenceNotFoundException( name );
        }
        if ( sequenceType.getRevision() != revision )
        {
            throw new ConcurrentModificationException(
                this.getSequenceMapper().map( sequenceType, new Sequence() ) );

        }

        final Sequence oldValue = this.getSequenceMapper().map( sequenceType, new Sequence() );
        this.fireVetoableSequenceChange( oldValue, sequence );

        sequenceType = this.getSequenceMapper().map( sequence, sequenceType );
        sequenceType.setRevision( sequenceType.getRevision() + 1L );
        sequenceType.setJpaDate( Calendar.getInstance() );
        this.getEntityManager().merge( sequenceType );

        final Sequence edited = this.getSequenceMapper().map( sequenceType, new Sequence() );
        this.fireSequenceChange( oldValue, edited );
        return edited;
    }

    public Sequence deleteSequence( final String name, final long revision )
    {
        if ( name == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "name", null ) );
        }

        final SequenceType sequenceType = this.getSequenceByName( name );

        if ( sequenceType == null )
        {
            throw new SequenceNotFoundException( name );
        }
        if ( sequenceType.getRevision() != revision )
        {
            throw new ConcurrentModificationException(
                this.getSequenceMapper().map( sequenceType, new Sequence() ) );

        }

        final Sequence deleted = this.getSequenceMapper().map( sequenceType, new Sequence() );
        this.fireVetoableSequenceChange( deleted, null );

        final SequencesType sequences = this.getSequencesList();
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
        this.fireSequenceChange( s, null );
        return deleted;
    }

    public Set<Sequence> searchSequences( final String name )
    {
        final Query query = this.getEntityManager().createNamedQuery(
            name == null ? FIND_ALL_SEQUENCES_QUERY : SEARCH_SEQUENCES_QUERY );

        query.setParameter( 1, this.getSequencesList().getName() );

        if ( name != null )
        {
            query.setParameter( 2, "%" + name + "%" );
        }

        final List<SequenceType> resultList = (List<SequenceType>) query.getResultList();
        final Set<Sequence> sequences = new HashSet<Sequence>( resultList.size() );

        for ( SequenceType s : resultList )
        {
            sequences.add( this.getSequenceMapper().map( s, new Sequence() ) );
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

        final SequenceType sequenceType = this.getSequenceByName( sequenceName );

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
        this.fireSequenceChange( oldValue, s );
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

        final SequenceType sequenceType = this.getSequenceByName( sequenceName );

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
        this.fireSequenceChange( oldValue, s );
        return values;
    }

    // SECTION-END
    // SECTION-START[DefaultSequenceDirectory]
    /** Constant for the name of the query for counting sequences. */
    private static final String COUNT_SEQUENCES_QUERY = "jomc-sequences-model-count-sequences";

    /** Constant for the name of the query for finding all sequences. */
    private static final String FIND_ALL_SEQUENCES_QUERY = "jomc-sequences-model-find-all-sequences";

    /** Constant for the name of the query for searching sequences. */
    private static final String SEARCH_SEQUENCES_QUERY = "jomc-sequences-model-search-sequences";

    /** Constant for the name of the query for finding a sequence by name. */
    private static final String FIND_SEQUENCE_BY_NAME_QUERY = "jomc-sequences-model-find-sequence-by-name";

    /** Constant for the name of the query for finding a sequence list by name. */
    private static final String FIND_SEQUENCES_BY_NAME_QUERY = "jomc-sequences-model-find-sequences-by-name";

    /** Capacity limit. */
    private static final BigInteger CAPACITY_LIMIT = BigInteger.valueOf( Integer.MAX_VALUE );

    /**
     * Gets a sequence for a given name.
     *
     * @param name The name of the sequence to return.
     *
     * @return The sequence with name {@code name}, or {@code null} if no sequence matching {@code name} exists.
     */
    protected SequenceType getSequenceByName( final String name )
    {
        try
        {
            final Query query = this.getEntityManager().createNamedQuery( FIND_SEQUENCE_BY_NAME_QUERY );
            query.setParameter( 1, this.getSequencesList().getName() );
            query.setParameter( 2, name );

            return (SequenceType) query.getSingleResult();
        }
        catch ( final NoResultException e )
        {
            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( e.getMessage() );
            }

            return null;
        }
    }

    /**
     * Gets the list of sequences matching this implementations directory name.
     *
     * @return The list of sequences matching this implementations directory name.
     */
    protected SequencesType getSequencesList()
    {
        final Query query = this.getEntityManager().createNamedQuery( FIND_SEQUENCES_BY_NAME_QUERY );
        query.setParameter( 1, this.getDirectoryName() );

        SequencesType sequencesType = null;

        try
        {
            sequencesType = (SequencesType) query.getSingleResult();
        }
        catch ( final NoResultException e )
        {
            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( e.getMessage() );
            }

            sequencesType = new SequencesType();
            sequencesType.setName( this.getDirectoryName() );
            sequencesType.setJpaDate( Calendar.getInstance() );
            this.getEntityManager().persist( sequencesType );

            if ( this.getLogger().isDebugEnabled() )
            {
                this.getLogger().debug( this.getSuccessfullyCreatedSequenceDirectoryMessage(
                    this.getLocale(), this.getDirectoryName() ) );

            }
        }

        return sequencesType;
    }

    /**
     * Checks the model to not have reached its maximum capacity.
     *
     * @throws CapacityLimitException if the model reached its maximum capacity.
     */
    protected void assertMaximumCapacityNotReached()
    {
        final Query query = this.getEntityManager().createNamedQuery( COUNT_SEQUENCES_QUERY );
        query.setParameter( 1, this.getSequencesList().getName() );

        final BigInteger count = BigInteger.valueOf( ( (Long) query.getSingleResult() ).longValue() );
        if ( count.longValue() >= CAPACITY_LIMIT.longValue() )
        {
            throw new CapacityLimitException( CAPACITY_LIMIT );
        }
    }

    /**
     * Notifies all available {@code SequenceChangeListener}s about a changed sequence.
     *
     * @param oldValue The entity having been changed or {@code null} if {@code newValue} got added to the directory.
     * @param newValue The value {@code oldValue} got changed to or {@code null} if {@code oldValue} got removed from
     * the directory.
     */
    protected void fireSequenceChange( final Sequence oldValue, final Sequence newValue )
    {
        SequenceChangeEvent sequenceChange = null;
        for ( SequenceChangeListener l : this.getSequenceChangeListener() )
        {
            if ( sequenceChange == null )
            {
                sequenceChange = new SequenceChangeEvent( this, oldValue, newValue );
            }

            l.sequenceChange( sequenceChange );
        }
    }

    /**
     * Notifies all available {@code SequenceChangeListener}s about a sequence about to change.
     *
     * @param oldValue The entity about to change or {@code null} if {@code newValue} is about to be added to the
     * directory.
     * @param newValue The value {@code oldValue} will change to or {@code null} if {@code oldValue} will be removed
     * from the directory.
     *
     * @throws SequenceVetoException if any available {@code SequenceChangeListener} chooses to veto the sequence
     * change.
     */
    protected void fireVetoableSequenceChange( final Sequence oldValue, final Sequence newValue )
    {
        SequenceChangeEvent sequenceChange = null;
        boolean vetoed = false;

        for ( VetoableSequenceChangeListener l : this.getVetoableSequenceChangeListener() )
        {
            if ( sequenceChange == null )
            {
                sequenceChange = new SequenceChangeEvent( this, oldValue, newValue );
            }

            try
            {
                l.vetoableSequenceChange( sequenceChange );
            }
            catch ( final SequenceVetoException e )
            {
                this.getLogger().error( e.getMessage() );
                vetoed = true;
            }
        }

        if ( vetoed )
        {
            throw new SequenceVetoException( sequenceChange );
        }
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultSequenceDirectory} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
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
     * Gets the {@code EntityManager} dependency.
     * <p>This method returns the "{@code JOMC Standalone RI}" object of the {@code javax.persistence.EntityManager} specification.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested.</p>
     * @return The {@code EntityManager} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private javax.persistence.EntityManager getEntityManager()
    {
        final javax.persistence.EntityManager _d = (javax.persistence.EntityManager) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "EntityManager" );
        assert _d != null : "'EntityManager' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code Locale} dependency.
     * <p>This method returns the "{@code default}" object of the {@code java.util.Locale} specification at specification level 1.1.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private java.util.Locale getLocale()
    {
        final java.util.Locale _d = (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "Locale" );
        assert _d != null : "'Locale' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code Logger} dependency.
     * <p>This method returns any available object of the {@code org.jomc.logging.Logger} specification at specification level 1.0.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * <p><b>Properties</b><dl>
     * <dt>"{@code name}"</dt>
     * <dd>Property of type {@code java.lang.String}.
     * </dd>
     * </dl>
     * @return The {@code Logger} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private org.jomc.logging.Logger getLogger()
    {
        final org.jomc.logging.Logger _d = (org.jomc.logging.Logger) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "Logger" );
        assert _d != null : "'Logger' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SequenceChangeListener} dependency.
     * <p>This method returns any available object of the {@code org.jomc.sequences.SequenceChangeListener} specification at specification level 1.0.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SequenceChangeListener} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private org.jomc.sequences.SequenceChangeListener[] getSequenceChangeListener()
    {
        final org.jomc.sequences.SequenceChangeListener[] _d = (org.jomc.sequences.SequenceChangeListener[]) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceChangeListener" );
        assert _d != null : "'SequenceChangeListener' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code SequenceMapper} dependency.
     * <p>This method returns the "{@code JOMC Sequences RI}" object of the {@code org.jomc.sequences.ri.SequenceMapper} specification at specification level 1.0.</p>
     * <p>That specification applies to {@code Singleton} scope. The singleton object is returned whenever requested and bound to this instance.</p>
     * @return The {@code SequenceMapper} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private org.jomc.sequences.ri.SequenceMapper getSequenceMapper()
    {
        final org.jomc.sequences.ri.SequenceMapper _d = (org.jomc.sequences.ri.SequenceMapper) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "SequenceMapper" );
        assert _d != null : "'SequenceMapper' dependency not found.";
        return _d;
    }

    /**
     * Gets the {@code VetoableSequenceChangeListener} dependency.
     * <p>This method returns any available object of the {@code org.jomc.sequences.VetoableSequenceChangeListener} specification at specification level 1.0.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code VetoableSequenceChangeListener} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
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
     * Gets the value of the {@code directoryName} property.
     * @return Name uniquely identifying the directory in a set of directories.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private java.lang.String getDirectoryName()
    {
        final java.lang.String _p = (java.lang.String) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "directoryName" );
        assert _p != null : "'directoryName' property not found.";
        return _p;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Messages]
    // <editor-fold defaultstate="collapsed" desc=" Generated Messages ">

    /**
     * Gets the text of the {@code illegalArgument} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal value ''{1}'' for argument ''{0}''.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ung&uuml;ltiger Wert ''{1}'' f&uuml;r Parameter ''{0}''.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param argumentName Format argument.
     * @param argumentValue Format argument.
     * @return The text of the {@code illegalArgument} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private String getIllegalArgumentMessage( final java.util.Locale locale, final java.lang.String argumentName, final java.lang.String argumentValue )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "illegalArgument", locale, argumentName, argumentValue );
        assert _m != null : "'illegalArgument' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code successfullyCreatedSequenceDirectory} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Sequence directory ''{0}'' created.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Sequenzverzeichnis ''{0}'' erstellt.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @param name Format argument.
     * @return The text of the {@code successfullyCreatedSequenceDirectory} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.JavaSources",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-16-SNAPSHOT/jomc-tools" )
    private String getSuccessfullyCreatedSequenceDirectoryMessage( final java.util.Locale locale, final java.lang.String name )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "successfullyCreatedSequenceDirectory", locale, name );
        assert _m != null : "'successfullyCreatedSequenceDirectory' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
