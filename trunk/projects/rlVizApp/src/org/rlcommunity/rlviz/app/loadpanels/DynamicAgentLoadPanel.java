/*
Copyright 2007 Brian Tanner
brian@tannerpages.com
http://brian.tannerpages.com

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/


package org.rlcommunity.rlviz.app.loadpanels;

import org.rlcommunity.rlviz.app.RLGlueLogic;
import rlVizLib.general.ParameterHolder;

public class DynamicAgentLoadPanel extends DynamicLoadPanel {

	public DynamicAgentLoadPanel(RLGlueLogic theGlueConnection){
		super(theGlueConnection,"No Agents Available");
	}
	
	public void updateList(){
		theNames=theGlueConnection.getAgentNameList();
		theParams=theGlueConnection.getAgentParamList();
		
		super.refreshList(theNames, "No Agents Available");
		
	}
        @Override
	public boolean load(String thisName, ParameterHolder thisP) {
		boolean loadCheck = theGlueConnection.loadAgent(thisName,thisP);
                if(loadCheck){
                    theGlueConnection.loadAgentVisualizer();
                    return true;
                }
                return false;
	}

	@Override
	public String getStringType() {
		return "Agent";
	}

}