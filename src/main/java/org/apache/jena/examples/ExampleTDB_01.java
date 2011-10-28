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

import java.io.InputStream;
import java.util.Iterator;

import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.base.file.Location;
import com.hp.hpl.jena.tdb.store.DatasetGraphTDB;
import com.hp.hpl.jena.util.FileManager;

public class ExampleTDB_01 {

    public static void main(String[] args) {
        FileManager fm = FileManager.get();
        fm.addLocatorClassLoader(ExampleTDB_01.class.getClassLoader());
        InputStream in = fm.open("data/data.nt");

        Location location = new Location ("tmp/TDB");
        DatasetGraphTDB dsg = TDBFactory.createDatasetGraph(location);

        TDBLoader.load(dsg, in, false);
        
        Lock lock = dsg.getLock();
        lock.enterCriticalSection(Lock.READ);
        try {
            Iterator<Quad> iter = dsg.find();
            while ( iter.hasNext() ) {
                Quad quad = iter.next();
                System.out.println(quad);
            }
        } finally {
            lock.leaveCriticalSection();
        }

        dsg.close();
    }

}
