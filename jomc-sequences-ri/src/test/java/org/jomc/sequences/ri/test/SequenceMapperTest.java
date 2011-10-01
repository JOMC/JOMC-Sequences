// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
package org.jomc.sequences.ri.test;

import java.util.Calendar;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.model.SequenceType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for the {@code SequenceMapper} specification.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.ri.test.SequenceMapperTest>}.
 *   It does not provide any specified objects.
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
public class SequenceMapperTest
{
    // SECTION-START[SequenceMapperTest]

    @Test public void testIllegalArguments() throws Exception
    {
        try
        {
            this.getSequenceMapper().map( null, new Sequence() );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }

        try
        {
            this.getSequenceMapper().map( new SequenceType(), null );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }

        try
        {
            this.getSequenceMapper().map( null, new SequenceType() );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }

        try
        {
            this.getSequenceMapper().map( new Sequence(), null );
        }
        catch ( final SequencesSystemException e )
        {
            assertNotNull( e.getMessage() );
            System.out.println( e );
        }
    }

    @Test public void testMap() throws Exception
    {
        final Sequence sequence = new Sequence();
        sequence.setIncrement( Long.MAX_VALUE );
        sequence.setMaximum( Long.MAX_VALUE );
        sequence.setMinimum( Long.MAX_VALUE );
        sequence.setName( "Sequence" );
        sequence.setValue( Long.MAX_VALUE );

        final SequenceType mappedSequenceType = new SequenceType();
        this.getSequenceMapper().map( sequence, mappedSequenceType );

        assertEquals( 0L, mappedSequenceType.getDate().toGregorianCalendar().getTimeInMillis() );
        assertEquals( Long.MAX_VALUE, mappedSequenceType.getIncrement() );
        assertEquals( 0L, mappedSequenceType.getJpaDate().getTimeInMillis() );
        assertEquals( 0L, mappedSequenceType.getJpaId() );
        assertEquals( 0L, mappedSequenceType.getJpaVersion() );
        assertEquals( Long.MAX_VALUE, mappedSequenceType.getMaximum() );
        assertEquals( Long.MAX_VALUE, mappedSequenceType.getMinimum() );
        assertEquals( "Sequence", mappedSequenceType.getName() );
        assertEquals( 0L, mappedSequenceType.getRevision() );
        assertEquals( Long.MAX_VALUE, mappedSequenceType.getValue() );

        final Calendar now = Calendar.getInstance();
        mappedSequenceType.setJpaDate( now );
        mappedSequenceType.setRevision( Long.MAX_VALUE );

        final Sequence mappedSequence = new Sequence();
        this.getSequenceMapper().map( mappedSequenceType, mappedSequence );

        assertEquals( now.getTimeInMillis(), mappedSequence.getDate() );
        assertEquals( Long.MAX_VALUE, mappedSequence.getIncrement() );
        assertEquals( Long.MAX_VALUE, mappedSequence.getMaximum() );
        assertEquals( Long.MAX_VALUE, mappedSequence.getMinimum() );
        assertEquals( "Sequence", mappedSequence.getName() );
        assertEquals( Long.MAX_VALUE, mappedSequence.getRevision() );
        assertEquals( Long.MAX_VALUE, mappedSequence.getValue() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code SequenceMapperTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
    public SequenceMapperTest()
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
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
