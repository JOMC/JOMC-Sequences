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
package org.jomc.sequences.ri;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Calendar;
import java.util.TimeZone;
import org.jomc.sequences.Sequence;
import org.jomc.sequences.SequencesSystemException;
import org.jomc.sequences.model.SequenceType;

// SECTION-START[Documentation]
// <editor-fold defaultstate="collapsed" desc=" Generated Documentation ">
/**
 * SequenceMapper reference implementation.
 * <p><b>Specifications</b><ul>
 * <li>{@code org.jomc.sequences.ri.SequenceMapper} {@code 1.0} {@code Multiton}</li>
 * </ul></p>
 * <p><b>Dependencies</b><ul>
 * <li>"{@link #getLocale Locale}"<blockquote>
 * Dependency on {@code java.util.Locale} at specification level 1.1 bound to an instance.</blockquote></li>
 * </ul></p>
 * <p><b>Messages</b><ul>
 * <li>"{@link #getIllegalArgumentMessage illegalArgument}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Illegal value ''{1}'' for argument ''{0}''.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Ung&uuml;ltiger Wert ''{1}'' f&uuml;r Parameter ''{0}''.</pre></td></tr>
 * </table>
 * <li>"{@link #getUnhandledExceptionMessage unhandledException}"<table>
 * <tr><td valign="top">English:</td><td valign="top"><pre>Unhandled exception.</pre></td></tr>
 * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Unbehandelte Ausnahme.</pre></td></tr>
 * </table>
 * </ul></p>
 *
 * @author <a href="mailto:schulte2005@users.sourceforge.net">Christian Schulte</a> 1.0
 * @version $Id$
 */
// </editor-fold>
// SECTION-END
// SECTION-START[Annotations]
// <editor-fold defaultstate="collapsed" desc=" Generated Annotations ">
@javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                             comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18-SNAPSHOT/jomc-tools" )
// </editor-fold>
// SECTION-END
public class DefaultSequenceMapper implements SequenceMapper
{
    // SECTION-START[SequenceMapper]

    public Sequence map( final SequenceType sequenceType, final Sequence sequence )
    {
        if ( sequenceType == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage(
                this.getLocale(), "sequenceType", null ) );

        }
        if ( sequence == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "sequence", null ) );
        }

        sequence.setIncrement( sequenceType.getIncrement() );
        sequence.setMaximum( sequenceType.getMaximum() );
        sequence.setMinimum( sequenceType.getMinimum() );
        sequence.setName( sequenceType.getName() );
        sequence.setValue( sequenceType.getValue() );
        this.injectRevision( sequence, sequenceType.getRevision() );
        this.injectDate( sequence, sequenceType.getJpaDate().getTimeInMillis() );
        return sequence;
    }

    public SequenceType map( final Sequence sequence, final SequenceType sequenceType )
    {
        if ( sequence == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage( this.getLocale(), "sequence", null ) );
        }
        if ( sequenceType == null )
        {
            throw new SequencesSystemException( this.getIllegalArgumentMessage(
                this.getLocale(), "sequenceType", null ) );

        }

        sequenceType.setIncrement( sequence.getIncrement() );
        sequenceType.setMaximum( sequence.getMaximum() );
        sequenceType.setMinimum( sequence.getMinimum() );
        sequenceType.setName( sequence.getName() );
        sequenceType.setValue( sequence.getValue() );
        sequenceType.setRevision( sequence.getRevision() );

        final Calendar c = Calendar.getInstance();
        c.setTimeZone( TimeZone.getTimeZone( "UTC" ) );
        c.setTimeInMillis( sequence.getDate() );
        sequenceType.setJpaDate( c );

        return sequenceType;
    }

    // SECTION-END
    // SECTION-START[DefaultSequenceMapper]
    /**
     * Sets the value of property {@code revision} of a given sequence using reflection.
     *
     * @param s The sequence to update.
     * @param value The new value for property {@code revision}.
     *
     * @throws SequencesSystemException if injecting {@code value} fails unexpectedly.
     */
    protected void injectRevision( final Sequence s, final long value )
    {
        this.injectFieldValue( s, "revision", Long.valueOf( value ) );
    }

    /**
     * Sets the value of property {@code date} of a given sequence using reflection.
     *
     * @param s The sequence to update.
     * @param value The new value for property {@code date}.
     *
     * @throws SequencesSystemException if injecting {@code value} fails unexpectedly.
     */
    protected void injectDate( final Sequence s, final long value )
    {
        this.injectFieldValue( s, "date", Long.valueOf( value ) );
    }

    /**
     * Sets the value of a field of a given object using reflection.
     *
     * @param object The object to update.
     * @param fieldName The name of the field to update.
     * @param value The new value for field {@code fieldName}.
     *
     * @throws SequencesSystemException if setting {@code value} fails unexpectedly.
     */
    protected void injectFieldValue( final Object object, final String fieldName, final Object value )
    {
        AccessController.doPrivileged( new PrivilegedAction<Object>()
        {

            public Object run()
            {
                Field field = null;

                try
                {
                    field = object.getClass().getDeclaredField( fieldName );
                    field.setAccessible( true );
                    field.set( object, value );
                    field.setAccessible( false );
                    return null;
                }
                catch ( final Exception e )
                {
                    throw new SequencesSystemException( getUnhandledExceptionMessage( getLocale() ), e );
                }
            }

        } );
    }

    // SECTION-END
    // SECTION-START[Constructors]
    // <editor-fold defaultstate="collapsed" desc=" Generated Constructors ">

    /** Creates a new {@code DefaultSequenceMapper} instance. */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18-SNAPSHOT/jomc-tools" )
    public DefaultSequenceMapper()
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
     * Gets the {@code Locale} dependency.
     * <p>This method returns the "{@code default}" object of the {@code java.util.Locale} specification at specification level 1.1.</p>
     * <p>That specification does not apply to any scope. A new object is returned whenever requested and bound to this instance.</p>
     * @return The {@code Locale} dependency.
     * @throws org.jomc.ObjectManagementException if getting the dependency instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18-SNAPSHOT/jomc-tools" )
    private java.util.Locale getLocale()
    {
        final java.util.Locale _d = (java.util.Locale) org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getDependency( this, "Locale" );
        assert _d != null : "'Locale' dependency not found.";
        return _d;
    }
    // </editor-fold>
    // SECTION-END
    // SECTION-START[Properties]
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
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18-SNAPSHOT/jomc-tools" )
    private String getIllegalArgumentMessage( final java.util.Locale locale, final java.lang.String argumentName, final java.lang.String argumentValue )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "illegalArgument", locale, argumentName, argumentValue );
        assert _m != null : "'illegalArgument' message not found.";
        return _m;
    }

    /**
     * Gets the text of the {@code unhandledException} message.
     * <p><b>Templates</b><br/><table>
     * <tr><td valign="top">English:</td><td valign="top"><pre>Unhandled exception.</pre></td></tr>
     * <tr><td valign="top">Deutsch:</td><td valign="top"><pre>Unbehandelte Ausnahme.</pre></td></tr>
     * </table></p>
     * @param locale The locale of the message to return.
     * @return The text of the {@code unhandledException} message.
     *
     * @throws org.jomc.ObjectManagementException if getting the message instance fails.
     */
    @javax.annotation.Generated( value = "org.jomc.tools.SourceFileProcessor",
                                 comments = "See http://jomc.sourceforge.net/jomc/1.0-alpha-18-SNAPSHOT/jomc-tools" )
    private String getUnhandledExceptionMessage( final java.util.Locale locale )
    {
        final String _m = org.jomc.ObjectManagerFactory.getObjectManager( this.getClass().getClassLoader() ).getMessage( this, "unhandledException", locale );
        assert _m != null : "'unhandledException' message not found.";
        return _m;
    }
    // </editor-fold>
    // SECTION-END
}
