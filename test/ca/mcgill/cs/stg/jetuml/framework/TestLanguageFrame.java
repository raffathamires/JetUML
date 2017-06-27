/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2016 by the contributors of the JetUML project.
 *
 * See: https://github.com/prmr/JetUML
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package ca.mcgill.cs.stg.jetuml.framework;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

import org.junit.Before;
import org.junit.Test;

import ca.mcgill.cs.stg.jetuml.UMLEditor;
import junit.framework.TestCase;

public class TestLanguageFrame 
{ 
	private Method aCreateFileFilter;
	private LanguageFrame aLanguageFrame; 
	
	@Before
	public void setup() throws Exception
	{
		aLanguageFrame = new LanguageFrame();
	}
	
	@Test
	public void testLocaleENG(){
		 
		Locale defaultLocale = Locale.getDefault();
		
		try {
			Locale.setDefault(new Locale("en", "US"));
			aLanguageFrame.setLocale(new Locale("en", "US"));
			assertEquals(Locale.getDefault(), aLanguageFrame.getLocale());
			assertEquals(Locale.US, aLanguageFrame.getLocale());
		} finally {
			Locale.setDefault(defaultLocale);
		}
	}
	
}
