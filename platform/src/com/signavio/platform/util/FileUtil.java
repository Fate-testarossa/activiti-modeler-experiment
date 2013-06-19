/*******************************************************************************
 * Signavio Core Components
 * Copyright (C) 2012  Signavio GmbH
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
 ******************************************************************************/
package com.signavio.platform.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {

    public static StringBuffer readFile(File file) throws IOException {
        FileInputStream fin =  new FileInputStream(file);
        StringBuffer result = new StringBuffer();

        String thisLine = "";
        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));

        while ((thisLine = myInput.readLine()) != null) {
            result.append(thisLine);
            result.append("\n");
        }

        myInput.close();
        fin.close();

        return result;
    }
}
