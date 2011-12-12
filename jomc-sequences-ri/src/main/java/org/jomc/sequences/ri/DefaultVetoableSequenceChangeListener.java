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

import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;
import org.jomc.sequences.SequenceChangeStatus;
import org.jomc.sequences.SequenceVetoException;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * VetoableSequenceChangeListener reference implementation.
 *
 * <p>
 *   This implementation is identified by {@code <org.jomc.sequences.ri.DefaultVetoableSequenceChangeListener>}.
 * </p>
 * <p>
 *   It provides objects named {@code <JOMC Sequences RI>} of the following specifications:
 *
 *   <ul>
 *     <li>{@code <org.jomc.sequences.VetoableSequenceChangeListener>} at specification level 1.0.</li>
 *   </ul>
 * </p>
 * <dl>
 *   <dt><b>Abstract:</b></dt><dd>No</dd>
 *   <dt><b>Final:</b></dt><dd>No</dd>
 *   <dt><b>Stateless:</b></dt><dd>Yes</dd>
 * </dl>
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
public class DefaultVetoableSequenceChangeListener
    implements
    org.jomc.sequences.VetoableSequenceChangeListener
{
    // SECTION-START[VetoableSequenceChangeListener]

    public void vetoableSequenceChange( final SequenceChangeEvent evt )
    {
        boolean valid = true;

        if ( evt.getNewSequence() != null )
        {
            if ( evt.getNewSequence().getName() == null
                 || evt.getNewSequence().getName().length() < this.getSequenceNameMinLength()
                 || evt.getNewSequence().getName().length() > this.getSequenceNameMaxLength() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_NAME ).add( new SequenceChangeStatus.InvalidString(
                    SequenceChangeStatus.ERROR, evt.getNewSequence().getName(), null,
                    this.getSequenceNameMinLength(), this.getSequenceNameMaxLength() ) );

            }

            if ( evt.getNewSequence().getMaximum() < evt.getNewSequence().getMinimum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_MAXIMUM ).add( new SequenceChangeStatus.InvalidNumber(
                    SequenceChangeStatus.ERROR, evt.getNewSequence().getMaximum(),
                    evt.getNewSequence().getMinimum(), null ) );

            }

            if ( evt.getNewSequence().getMinimum() > evt.getNewSequence().getMaximum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_MINIMUM ).add( new SequenceChangeStatus.InvalidNumber(
                    SequenceChangeStatus.ERROR, evt.getNewSequence().getMinimum(),
                    evt.getNewSequence().getValue(), null ) );

            }

            if ( evt.getNewSequence().getValue() > evt.getNewSequence().getMaximum()
                 || evt.getNewSequence().getValue() < evt.getNewSequence().getMinimum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_VALUE ).add( new SequenceChangeStatus.InvalidNumber(
                    SequenceChangeStatus.ERROR, evt.getNewSequence().getValue(),
                    evt.getNewSequence().getMinimum(), evt.getNewSequence().getMaximum() ) );

            }

            if ( evt.getNewSequence().getIncrement() <= 0L )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_INCREMENT ).add( new SequenceChangeStatus.InvalidNumber(
                    SequenceChangeStatus.ERROR, evt.getNewSequence().getIncrement(), 1L, null ) );

            }
        }

        if ( !valid )
        {
            throw new SequenceVetoException( evt );
        }
    }

    // SECTION-END
    // SECTION-START[DefaultVetoableSequenceChangeListener]
    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">
    /** Creates a new {@code DefaultVetoableSequenceChangeListener} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public DefaultVetoableSequenceChangeListener()
    {
        // SECTION-START[Default Constructor]
        super();
        // SECTION-END
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // <editor-fold defaultstate="collapsed" desc=" Generated Properties ">
    /**
     * Gets the value of the {@code <sequenceNameMaxLength>} property.
     * <p>
     *   <dl>
     *     <dt><b>Final:</b></dt><dd>No</dd>
     *   </dl>
     * </p>
     * @return Maximum allowed length of a sequence name.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private int getSequenceNameMaxLength()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceNameMaxLength" );
        assert _p != null : "'sequenceNameMaxLength' property not found.";
        return _p.intValue();
    }
    /**
     * Gets the value of the {@code <sequenceNameMinLength>} property.
     * <p>
     *   <dl>
     *     <dt><b>Final:</b></dt><dd>No</dd>
     *   </dl>
     * </p>
     * @return Minimum required length of a sequence name.
     * @throws org.jomc.ObjectManagementException if getting the property instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    private int getSequenceNameMinLength()
    {
        final java.lang.Integer _p = (java.lang.Integer) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getProperty( this, "sequenceNameMinLength" );
        assert _p != null : "'sequenceNameMinLength' property not found.";
        return _p.intValue();
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
