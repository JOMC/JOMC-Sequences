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
package org.jomc.sequences;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Sequence of numbers.
 *
 * <p>
 *   This specification is identified by identifier {@code <org.jomc.sequences.Sequence>}.
 *   An application assembler may provide multiple implementations of this specification (including none) due to
 *   multiplicity {@code <Many>}.
 *   This specification does not apply to any scope. A new object is returned whenever requested.
 * </p>
 *
 * <p>
 *   Use of class {@code ObjectManager} is supported for accessing implementations.
 *   <blockquote><pre>
 * Sequence[] objects = ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( Sequence[].class );
 * Sequence object = ObjectManagerFactory.getObjectManager( getClass().getClassLoader() ).getObject( Sequence.class, "<i>implementation name</i>" );
 *   </pre></blockquote>
 * </p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version 1.0
 * @see org.jomc.ObjectManagerFactory
 * @see org.jomc.ObjectManager
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor 1.2-SNAPSHOT", comments =
"See http://jomc.sourceforge.net/jomc/1.2/jomc-tools-1.2-SNAPSHOT" )
// </editor-fold>
// SECTION-END
public interface Sequence
{
    // SECTION-START[Sequence]

    /** Constant for the name of property {@code name}. */
    String PROP_NAME = "org.jomc.sequences.Sequence.PROP_NAME";

    /** Constant for the name of property {@code minimum}. */
    String PROP_MINIMUM = "org.jomc.sequences.Sequence.PROP_MINIMUM";

    /** Constant for the name of property {@code maximum}. */
    String PROP_MAXIMUM = "org.jomc.sequences.Sequence.PROP_MAXIMUM";

    /** Constant for the name of property {@code increment}. */
    String PROP_INCREMENT = "org.jomc.sequences.Sequence.PROP_INCREMENT";

    /** Constant for the name of property {@code value}. */
    String PROP_VALUE = "org.jomc.sequences.Sequence.PROP_VALUE";

    /**
     * Gets the revision of the entity.
     *
     * @return The revision of the entity.
     */
    long getRevision();

    /**
     * Gets the date of the revision of the entity.
     *
     * @return The date of the revision of the entity.
     */
    long getDate();

    /**
     * Gets the logical name of the sequence.
     *
     * @return The logical name of the sequence.
     */
    String getName();

    /**
     * Gets the minimum value of property {@code value}.
     *
     * @return The minimum value of property {@code value}.
     */
    long getMinimum();

    /**
     * Gets the maximum value of property {@code value}.
     *
     * @return The maximum value of property {@code value}.
     */
    long getMaximum();

    /**
     * Gets the delta to add to the value of property {@code value} for the next value in the sequence.
     *
     * @return The the delta to add to the value of property {@code value} for the next value in the sequence.
     */
    long getIncrement();

    /**
     * Gets the current value of the sequence.
     *
     * @return The current value of the sequence.
     */
    long getValue();

    // SECTION-END
}
