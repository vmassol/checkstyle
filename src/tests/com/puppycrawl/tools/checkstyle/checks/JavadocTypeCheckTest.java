package com.puppycrawl.tools.checkstyle.checks;

import com.puppycrawl.tools.checkstyle.BaseCheckTestCase;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.Scope;

/**
 * @author Oliver.Burn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class JavadocTypeCheckTest extends BaseCheckTestCase
{
    public void testTags() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        final String[] expected =
        {
            "8: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputTags.java"), expected);
    }

    public void testInner() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        final String[] expected =
        {
            "14: Missing a Javadoc comment.",
            "21: Missing a Javadoc comment.",
            "27: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputInner.java"), expected);
    }

    public void testStrict() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        final String[] expected =
        {
            "7: Missing a Javadoc comment.",
            "9: Missing a Javadoc comment.",
            "14: Missing a Javadoc comment.",
            "34: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputPublicOnly.java"), expected);
    }

    public void testProtected() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("scope", Scope.PROTECTED.getName());
        final String[] expected =
        {
            "7: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputPublicOnly.java"), expected);
    }

    public void testPublic() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("scope", Scope.PUBLIC.getName());
        final String[] expected =
        {
            "7: Missing a Javadoc comment.",
            "38: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputScopeInnerInterfaces.java"), expected);
    }

    public void testProtest() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("scope", Scope.PROTECTED.getName());
        final String[] expected =
        {
            "7: Missing a Javadoc comment.",
            "29: Missing a Javadoc comment.",
            "38: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputScopeInnerInterfaces.java"), expected);
    }

    public void testPkg() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute(
            "scope",
            Scope.getInstance("package").getName());
        final String[] expected =
        {
            "18: Missing a Javadoc comment.",
            "20: Missing a Javadoc comment.",
            "22: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputScopeInnerClasses.java"), expected);
    }

    public void testEclipse() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute(
            "scope",
            Scope.getInstance("public").getName());
        final String[] expected =
        {
            "18: Missing a Javadoc comment.",
        };
        verify(checkConfig, getPath("InputScopeInnerClasses.java"), expected);
    }

    public void testAuthorRequired() throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("authorFormat", "\\S");
        final String[] expected =
        {
            "13: Type Javadoc comment is missing an @author tag.",
        };
        verify(checkConfig, getPath("InputWhitespace.java"), expected);
    }
    
    public void testAuthorRegularEx()
        throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("authorFormat", "0*");
        final String[] expected = {
        };
        verify(checkConfig, getPath("InputJavadoc.java"), expected);
    }

    public void testAuthorRegularExError()
        throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("authorFormat", "ABC");
        final String[] expected = {
            "13: Type Javadoc comment is missing an @author tag.",
        };
        verify(checkConfig, getPath("InputJavadoc.java"), expected);
    }

    public void testVersionRequired()
        throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("versionFormat", "\\S");
        final String[] expected = {
            "13: Type Javadoc comment is missing an @version tag."
        };
        verify(checkConfig, getPath("InputWhitespace.java"), expected);
    }
    
    public void testVersionRegularEx()
        throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("versionFormat", "[:digit:].*");
        final String[] expected = {
        };
        verify(checkConfig, getPath("InputJavadoc.java"), expected);
    }
    
    public void testVersionRegularExError()
        throws Exception
    {
        final DefaultConfiguration checkConfig =
            createCheckConfig(JavadocTypeCheck.class);
        checkConfig.addAttribute("versionFormat", "\\$Revision.*\\$");
        final String[] expected = {
            "13: Type Javadoc comment is missing an @version tag."
        };
        verify(checkConfig, getPath("InputJavadoc.java"), expected);
    }
}