// SECTION-START[License Header]
// <editor-fold defaultstate="collapsed" desc=" Generated License ">
/*
 *   Copyright (C) Christian Schulte, 2005-07-25
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

import java.io.Serializable;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * Status of a &quot;SequenceChange&quot; event.
 *
 * <p>
 *   This implementation is identified by identifier {@code <org.jomc.sequences.SequenceChangeStatus>}.
 *   It does not provide any specified objects due to flag {@code <abstract>}.
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
public abstract class SequenceChangeStatus implements Serializable
{
    // SECTION-START[SequenceChangeStatus]

    /** Constant for an information. */
    public static final int INFORMATION = 1;

    /** Constant for a notification. */
    public static final int NOTIFICATION = 2;

    /** Constant for a warning. */
    public static final int WARNING = 3;

    /** Constant for an error. */
    public static final int ERROR = 4;

    /** Serial version UID for backwards compatibility with 1.0.x classes. */
    private static final long serialVersionUID = -8498327475722743081L;

    /**
     * Type of the status.
     * @serial
     */
    private int type;

    /**
     * Identifier of the status.
     * @serial
     */
    private String identifier;

    /**
     * Creates a new {@code SequenceChangeStatus} instance taking a type constant and an identifier of the instance.
     *
     * @param type The type of the new status.
     * @param identifier The identifier of the status.
     */
    public SequenceChangeStatus( final int type, final String identifier )
    {
        super();
        this.type = type;
        this.identifier = identifier;
    }

    /**
     * Gets the type of the status.
     *
     * @return The type of the status.
     */
    public final int getType()
    {
        return this.type;
    }

    /**
     * Gets the identifier of the status.
     *
     * @return The identifier of the status.
     */
    public final String getIdentifier()
    {
        return this.identifier;
    }

    /**
     * {@code SequenceChangeStatus} denoting an invalid string.
     *
     * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
     * @version $JOMC$
     * @see SequenceChangeEvent#getStatus(java.lang.String, java.lang.Class)
     */
    public static final class InvalidString extends SequenceChangeStatus implements Serializable
    {

        /** Constant for the identifier of status instances denoting invalid strings. */
        public static final String IDENTIFIER = "org.jomc.sequences.SequenceChangeStatus.InvalidString.IDENTIFIER";

        /** Serial version UID for backwards compatibility with 1.0.x classes. */
        private static final long serialVersionUID = -7118105105174457212L;

        /**
         * The invalid string.
         * @serial
         */
        private String invalidString;

        /**
         * The invalid characters of the string.
         * @serial
         */
        private char[] invalidCharacters;

        /**
         * The minimum required length.
         * @serial
         */
        private Number minimumLength;

        /**
         * The maximum allowed length.
         * @serial
         */
        private Number maximumLength;

        /**
         * Creates a new {@code InvalidString} instance taking a type constant, an invalid string, an array of
         * invalid characters, a minimum required length and a maximum allowed length.
         *
         * @param type The type of the new status.
         * @param invalidString The invalid string or {@code null} if no such string is known.
         * @param invalidCharacters The invalid characters or {@code null} if no such characters are known.
         * @param minimumLength The minimum required length or {@code null} if no such requirement exists.
         * @param maximumLength The maximum allowed length or {@code null} if no such limit exists.
         */
        public InvalidString( final int type, final String invalidString, final char[] invalidCharacters,
                              final Number minimumLength, final Number maximumLength )
        {
            super( type, IDENTIFIER );
            this.invalidString = invalidString;
            this.minimumLength = minimumLength;
            this.maximumLength = maximumLength;

            if ( invalidCharacters != null )
            {
                this.invalidCharacters = new char[ invalidCharacters.length ];
                System.arraycopy( invalidCharacters, 0, this.invalidCharacters, 0, invalidCharacters.length );
            }
            else
            {
                this.invalidCharacters = null;
            }
        }

        /**
         * Gets the invalid string.
         *
         * @return The invalid string or {@code null} if no such string is known.
         */
        public String getInvalidString()
        {
            return this.invalidString;
        }

        /**
         * Gets the invalid characters.
         *
         * @return The invalid characters or {@code null} if no such characters are known.
         */
        public char[] getInvalidCharacters()
        {
            if ( this.invalidCharacters != null )
            {
                final char[] copy = new char[ this.invalidCharacters.length ];
                System.arraycopy( this.invalidCharacters, 0, copy, 0, this.invalidCharacters.length );
                return copy;
            }

            return null;
        }

        /**
         * Gets the minimum required length.
         *
         * @return The minimum required length or {@code null} if no such requirement exists.
         */
        public Number getMinimumLength()
        {
            return this.minimumLength;
        }

        /**
         * Gets the maximum allowed length.
         *
         * @return The maximum allowed length or {@code null} if no such limit exists.
         */
        public Number getMaximumLength()
        {
            return this.maximumLength;
        }

        /**
         * Returns a string representation of the object.
         *
         * @return A string representation of the object.
         */
        @Override
        public String toString()
        {
            final StringBuilder str = new StringBuilder().append( "{" );
            String characters = null;

            if ( this.invalidCharacters != null && this.invalidCharacters.length > 0 )
            {
                final StringBuilder builder = new StringBuilder( this.invalidCharacters.length * 2 );

                for ( char c : this.invalidCharacters )
                {
                    builder.append( "," ).append( c );
                }

                characters = "[" + builder.substring( 1 ) + "]";
            }

            str.append( "type=" ).append( this.getType() ).
                append( ", identifier=" ).append( this.getIdentifier() ).
                append( ", invalidString=" ).append( this.getInvalidString() ).
                append( ", invalidCharacters=" ).append( characters ).
                append( ", minimumLength=" ).append( this.getMinimumLength() ).
                append( ", maximumLength=" ).append( this.getMaximumLength() ).
                append( '}' );

            return super.toString() + str.toString();
        }

    }

    /**
     * {@code SequenceChangeStatus} denoting an invalid number.
     *
     * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
     * @version $JOMC$
     * @see SequenceChangeEvent#getStatus(java.lang.String, java.lang.Class)
     */
    public static final class InvalidNumber extends SequenceChangeStatus implements Serializable
    {

        /** Constant for the identifier of status instances denoting invalid numbers. */
        public static final String IDENTIFIER = "org.jomc.sequences.SequenceChangeStatus.InvalidNumber.IDENTIFIER";

        /** Serial version UID for backwards compatibility with 1.0.x classes. */
        private static final long serialVersionUID = 6588819332070168270L;

        /**
         * The invalid number.
         * @serial
         */
        private Number invalidNumber;

        /**
         * The minimum required value.
         * @serial
         */
        private Number minimum;

        /**
         * The maximum allowed value.
         * @serial
         */
        private Number maximum;

        /**
         * Creates a new {@code InvalidNumber} instance taking a type constant, an invalid number, a minimum
         * required value and a maximum allowed value.
         *
         * @param type The type of the new status.
         * @param invalidNumber The invalid number or {@code null} if no such number is known.
         * @param minimum The minimum required value or {@code null} if no such requirement exists.
         * @param maximum The maximum allowed value or {@code null} if no such limit exists.
         */
        public InvalidNumber( final int type, final Number invalidNumber, final Number minimum,
                              final Number maximum )
        {
            super( type, IDENTIFIER );
            this.invalidNumber = invalidNumber;
            this.minimum = minimum;
            this.maximum = maximum;
        }

        /**
         * Gets the invalid number.
         *
         * @return The invalid number or {@code null} if no such number is known.
         */
        public Number getInvalidNumber()
        {
            return this.invalidNumber;
        }

        /**
         * Gets the minimum required value.
         *
         * @return The minimum required value or {@code null} if no such requirement exists.
         */
        public Number getMinimum()
        {
            return this.minimum;
        }

        /**
         * Gets the maximum allowed value.
         *
         * @return The maximum allowed value or {@code null} if no such limit exists.
         */
        public Number getMaximum()
        {
            return this.maximum;
        }

        /**
         * Returns a string representation of the object.
         *
         * @return A string representation of the object.
         */
        @Override
        public String toString()
        {
            final StringBuilder str = new StringBuilder().append( "{" );
            str.append( "type=" ).append( this.getType() ).
                append( ", identifier=" ).append( this.getIdentifier() ).
                append( ", invalidNumber=" ).append( this.getInvalidNumber() ).
                append( ", minimum=" ).append( this.getMinimum() ).
                append( ", maximum=" ).append( this.getMaximum() ).
                append( '}' );

            return super.toString() + str.toString();
        }

    }
    // SECTION-END
    // SECTION-START[Dependencies]
    // SECTION-END
    // SECTION-START[Properties]
    // SECTION-END
    // SECTION-START[Messages]
    // SECTION-END
}
