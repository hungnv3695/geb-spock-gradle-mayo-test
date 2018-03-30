package module

import geb.Module

class ProgressElementModule extends Module{
	static content = {
		module_progress {$(".module_progress")}
		element1 {module_progress.find("li", 0)}
		element2 {module_progress.find("li", 1)}
		element3 {module_progress.find("li", 2)}
		element4 {module_progress.find("li", 3)}
		
		element1_p1 {element1.find("p", 0)}
		element1_p2 {element1.find("p", 1)}
		
		element2_p1 {element2.find("p", 0)}
		element2_p2 {element2.find("p", 1)}
		
		element3_p1 {element3.find("p", 0)}
		element3_p2 {element3.find("p", 1)}
		
		element4_p1 {element4.find("p", 0)}
		element4_p2 {element4.find("p", 1)}
		
	}
}
