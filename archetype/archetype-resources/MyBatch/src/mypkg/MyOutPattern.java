#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright 2017 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mypkg;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Properties;

import com.ibm.websphere.batch.devframework.datastreams.patternadapter.FileWriterPattern;

public class MyOutPattern implements FileWriterPattern {

	@Override
	public void initialize(Properties arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeHeader(BufferedWriter arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeHeader(BufferedWriter arg0, Object arg1) throws IOException {
	}

	@Override
	public void writeRecord(BufferedWriter arg0, Object arg1) throws IOException {
		arg0.write((String)arg1);
		arg0.newLine();
		arg0.write("nov1");
		arg0.newLine();
	}

}
