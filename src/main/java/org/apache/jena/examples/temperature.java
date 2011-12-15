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

import java.util.List;

import com.hp.hpl.jena.query.QueryBuildException;
import com.hp.hpl.jena.sparql.expr.ExprEvalException;
import com.hp.hpl.jena.sparql.expr.ExprList;
import com.hp.hpl.jena.sparql.expr.NodeValue;
import com.hp.hpl.jena.sparql.function.FunctionBase;

public class temperature extends FunctionBase {

    public static final double fahrenheitToCelsiusConstant = 5.0 / 9.0 ;
    public static final double rankineToCelsiusConstant = 5.0 / 9.0 ;
    
    public temperature() { 
        super() ; 
    }

    @Override 
    public void checkBuild(String uri, ExprList args) {
        if ( args.size() != 1 ) 
            throw new QueryBuildException("Function '"+com.hp.hpl.jena.sparql.util.Utils.className(this)+"' takes one argument") ;
    }

    @Override
    public NodeValue exec(List<NodeValue> args) {
        if ( args.size() > 1 )
            throw new ExprEvalException("replace: Wrong number of arguments: "+args.size()+" : [wanted 1]") ;
        
        NodeValue v1 = args.get(0) ;
        
        return convert(v1) ;
    }
    
    private static NodeValue convert(NodeValue nv)
    {
        if ( nv.getNode().getLiteralDatatype() instanceof TemperatureFahrenheit ) {
            return NodeValue.makeDouble((nv.getDouble() - 32.0) * fahrenheitToCelsiusConstant);
        } else if ( nv.getNode().getLiteralDatatype() instanceof TemperatureKelvin ) {
            return NodeValue.makeDouble(nv.getDouble() - 273.15);
        } else if ( nv.getNode().getLiteralDatatype() instanceof TemperatureRankine ) {
            return NodeValue.makeDouble((nv.getDouble() - 491.67) * rankineToCelsiusConstant);
        } else {
            return nv ;                
        }
    }
        
}

