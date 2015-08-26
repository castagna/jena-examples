/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.examples;

import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;

public class ExampleLARQ_01 {

	public static void main(String[] args) throws CorruptIndexException, IOException {
//        FileManager fm = FileManager.get();
//        fm.addLocatorClassLoader(ExampleTDB_01.class.getClassLoader());
//        InputStream in = fm.open("data/data.nt");
//
//        Location location = Location.create ("tmp/TDB");
//        // build the Lucene index when pointed to a non existing directory
//        DatasetGraphTDB dsg = (DatasetGraphTDB)TDBFactory.createDatasetGraph(location);
//
//        TDBLoader.load(dsg, in, false);
//        
//        IndexLARQ index = AssemblerLARQ.make(dsg.toDataset(), "tmp/lucene");
//        NodeIterator iter = index.searchModelByIndex("A*"); // use Lucene syntax here
//        while ( iter.hasNext() ) {
//        	System.out.println(iter.next());
//        }
	}

}
