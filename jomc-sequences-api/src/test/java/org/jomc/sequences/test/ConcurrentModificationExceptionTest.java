// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (c) 2010 The JOMC Project
 *   Copyright (c) 2005 Christian Schulte <schulte2005@users.sourceforge.net>
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
package org.jomc.sequences.test;

import java.io.ObjectInputStream;
import java.net.URL;
import org.jomc.sequences.ConcurrentModificationException;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Test cases for class {@code org.jomc.sequences.ConcurrentModificationException}.
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18/jomc-tools" )
// </editor-fold>
// SECTION-END
public class ConcurrentModificationExceptionTest
{
    // SECTION-START[ConcurrentModificationExceptionTest]

    @Test public void testSerializable() throws Exception
    {
        final URL ser = this.getClass().getResource( "ConcurrentModificationException.ser" );
        assertNotNull( ser );

        final ObjectInputStream in = new ObjectInputStream( ser.openStream() );
        final ConcurrentModificationException e = (ConcurrentModificationException) in.readObject();
        in.close();

        assertNotNull( e.getMostRecentRevision() );
        assertEquals( 0L, e.getMostRecentRevision().getDate() );
        assertEquals( Long.MAX_VALUE, e.getMostRecentRevision().getIncrement() );
        assertEquals( Long.MAX_VALUE, e.getMostRecentRevision().getMaximum() );
        assertEquals( Long.MAX_VALUE, e.getMostRecentRevision().getMinimum() );
        assertEquals( "Sequence 1", e.getMostRecentRevision().getName() );
        assertEquals( 0L, e.getMostRecentRevision().getRevision() );
        assertEquals( Long.MAX_VALUE, e.getMostRecentRevision().getValue() );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code ConcurrentModificationExceptionTest} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18/jomc-tools" )
    public ConcurrentModificationExceptionTest()
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
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
