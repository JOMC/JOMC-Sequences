// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2009 - 2011 The JOMC Project
 *   Copyright (c) 2005 - 2011 Christian Schulte <schulte2005@users.sourceforge.net>
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

import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequenceChangeEvent;
import org.jomc.sequences.SequenceVetoException;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * VetoableSequenceChangeListener reference implementation.
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="4" nowrap><font size="+2">Specifications</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Identifier</b></td>
 *       <td align="left" scope="col" nowrap><b>Class</b></td>
 *       <td align="left" scope="col" nowrap><b>Scope</b></td>
 *       <td align="left" scope="col" nowrap><b>Version</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@code org.jomc.sequences.VetoableSequenceChangeListener}</td>
 *       <td align="left" nowrap>{@code org.jomc.sequences.VetoableSequenceChangeListener}</td>
 *       <td align="left" nowrap>{@code Multiton}</td>
 *       <td align="left" nowrap>{@code 1.0}</td>
 *     </tr>
 *   </table>
 * </p>
 * <p>
 *   <table border="1" width="100%" cellpadding="3" cellspacing="0">
 *     <tr class="TableHeadingColor">
 *       <th align="left" scope="col" colspan="3" nowrap><font size="+2">Properties</font></th>
 *     </tr>
 *     <tr class="TableSubHeadingColor">
 *       <td align="left" scope="col" nowrap><b>Name</b></td>
 *       <td align="left" scope="col" nowrap><b>Type</b></td>
 *       <td align="left" scope="col" nowrap><b>Documentation</b></td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@link #getSequenceNameMaxLength sequenceNameMaxLength}</td>
 *       <td align="left" nowrap>{@code int}</td>
 *       <td align="left" valign="top">Maximum allowed length of a sequence name.</td>
 *     </tr>
 *     <tr class="TableRowColor">
 *       <td align="left" nowrap>{@link #getSequenceNameMinLength sequenceNameMinLength}</td>
 *       <td align="left" nowrap>{@code int}</td>
 *       <td align="left" valign="top">Minimum required length of a sequence name.</td>
 *     </tr>
 *   </table>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
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
            if ( evt.getNewSequence().getName() == null )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_NAME ).add( SequenceChangeEvent.MANDATORY_VALUE );
            }
            else if ( evt.getNewSequence().getName().length() < this.getSequenceNameMinLength()
                      || evt.getNewSequence().getName().length() > this.getSequenceNameMaxLength() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_NAME ).add( SequenceChangeEvent.ILLEGAL_LENGTH );
            }

            if ( evt.getNewSequence().getMaximum() < evt.getNewSequence().getMinimum()
                 || evt.getNewSequence().getMinimum() > evt.getNewSequence().getMaximum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_MINIMUM ).add( SequenceChangeEvent.ILLEGAL_VALUE );
                evt.getStatus( Sequence.PROP_MAXIMUM ).add( SequenceChangeEvent.ILLEGAL_VALUE );
            }

            if ( evt.getNewSequence().getValue() > evt.getNewSequence().getMaximum()
                 || evt.getNewSequence().getValue() < evt.getNewSequence().getMinimum() )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_VALUE ).add( SequenceChangeEvent.ILLEGAL_VALUE );
            }

            if ( evt.getNewSequence().getIncrement() <= 0L )
            {
                valid = false;
                evt.getStatus( Sequence.PROP_INCREMENT ).add( SequenceChangeEvent.ILLEGAL_VALUE );
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
     * Gets the value of the {@code sequenceNameMaxLength} property.
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
     * Gets the value of the {@code sequenceNameMinLength} property.
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
