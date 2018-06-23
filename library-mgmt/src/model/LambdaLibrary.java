package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {
	// for model.Publication.getNextAvailableCopyLambda()
	public final static Function<List<LendableCopy>, List<LendableCopy>> NEXTAVAILABLECOPY
	   		= list -> list.stream()
                     .filter(c -> !c.isCheckOut())
                     .collect(Collectors.toList());
	
	// for CheckoutRecord.printEntries(List<CheckoutRecordEntry> entries)
	public final static Consumer<List<CheckoutRecordEntry>> PRINTENTRIES
		   = list -> list.forEach(System.out::println);

	//for services.UserService.getAllMembersLambda
	public final static Function<HashMap<String, LibraryMember>, List<LibraryMember>> GETALLMEMBERS
			= (hashMap) -> hashMap.entrySet()
						.stream()
						.map(Map.Entry::getValue)
						.collect(Collectors.toList());
}