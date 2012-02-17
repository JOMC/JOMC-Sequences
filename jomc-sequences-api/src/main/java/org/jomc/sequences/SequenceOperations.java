// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Java Object Management and Configuration
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
package org.jomc.sequences;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Provides operations performed with sequences.
 *
 * <dl>
 *   <dt><b>Identifier:</b></dt><dd>org.jomc.sequences.SequenceOperations</dd>
 *   <dt><b>Multiplicity:</b></dt><dd>One</dd>
 *   <dt><b>Scope:</b></dt><dd>Singleton</dd>
 * </dl>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManager#getObject(java.lang.Class) getObject(SequenceOperations.class)
 * @see org.jomc.ObjectManager#getObject(java.lang.Class,java.lang.String) getObject(SequenceOperations.class, "<i>implementation name</i>")
 * @see org.jomc.ObjectManagerFactory
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2", comments = "See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2" )
// </editor-fold>
// SECTION-END
public interface SequenceOperations
{
    // SECTION-START[SequenceOperations]

    /**
     * Gets the next value for a named sequence.
     *
     * @param sequenceName The name of the sequence to get the next value of.
     *
     * @return The next value of the sequence with name {@code name}.
     *
     * @throws SequenceNotFoundException if no sequence exists for {@code sequenceName}.
     * @throws SequenceLimitException if the sequence with name {@code sequenceName} reached its maximum value.
     * @throws SequencesSystemException if getting the value fails.
     */
    long getNextSequenceValue( String sequenceName )
        throws SequenceNotFoundException, SequenceLimitException, SequencesSystemException;

    /**
     * Gets multiple next values for a named sequence.
     *
     * @param sequenceName The name of the sequence to get values of.
     * @param numValues The number of values to get from the sequence with name {@code sequenceName} - must be positive.
     *
     * @return An array of next values of the sequence with name {@code name} with a length equal to {@code numValues}.
     *
     * @throws SequenceNotFoundException if no sequence exists for {@code sequenceName}.
     * @throws SequenceLimitException if the sequence with name {@code sequenceName} reached its maximum value.
     * @throws SequencesSystemException if getting values fails.
     */
    long[] getNextSequenceValues( String sequenceName, int numValues )
        throws SequenceNotFoundException, SequenceLimitException, SequencesSystemException;

    // SECTION-END
}
