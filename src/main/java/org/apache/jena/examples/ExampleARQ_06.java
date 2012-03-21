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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.core.Var;
import com.hp.hpl.jena.sparql.engine.binding.Binding;
import com.hp.hpl.jena.sparql.serializer.SerializationContext;
import com.hp.hpl.jena.sparql.util.FmtUtils;
import com.hp.hpl.jena.util.FileManager;

public class ExampleARQ_06 {

	public static void main(String[] args) throws IOException {
		FileManager.get().addLocatorClassLoader(ExampleARQ_01.class.getClassLoader());
		Model model = FileManager.get().loadModel("data/data.ttl");
		Query query = QueryFactory.create("SELECT * WHERE { ?s ?p ?o }");
		QueryExecution qexec = QueryExecutionFactory.create(query, model);

		FileOutputStream out = new FileOutputStream("target/sxssf.xlsx");
		Workbook wb = new SXSSFWorkbook(100);
		Sheet sh = wb.createSheet();

		int rows = 0;
		int columns = 0;
		try {
			ResultSet resultSet = qexec.execSelect();
			List<String> varNames = resultSet.getResultVars();
			List<Var> vars = new ArrayList<Var>(varNames.size());

			// first row with var names
			Row row = sh.createRow(rows++);
			for (String varName : varNames) {
				Var var = Var.alloc(varName);
				Cell cell = row.createCell(columns++);
				cell.setCellValue(var.toString());
				vars.add(var);
			}

			// other rows with bindings
			while (resultSet.hasNext()) {
				Binding bindings = resultSet.nextBinding();
				row = sh.createRow(rows++);
				columns = 0;
				for (Var var : vars) {
					Node n = bindings.get(var);
					if (n != null) {
						Cell cell = row.createCell(columns++);
						String value = FmtUtils.stringForNode(n, (SerializationContext) null);
						cell.setCellValue(value);
					}
				}
			}
		} finally {
			qexec.close();
		}

		wb.write(out);
		out.close();
	}

}
