package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LambdaLibrary {
	// for model.Publication.getNextAvailableCopyLambda()
	public final static Function<List<LendableCopy>, List<LendableCopy>> NEXTAVAILABLECOPY
	   		= (list) -> list.stream()
                     .filter(c -> c.isCheckOut() == false)
                     .collect(Collectors.toList());
	
	// for CheckoutRecord.printEntries(List<CheckoutRecordEntry> entries)
	public final static Consumer<List<CheckoutRecordEntry>> PRINTENTRIES
		   = (list) -> list.stream()
		   				.forEach(c -> System.out.println(c.toString()));

	//for Services.UserService.getAllMembersLambda
	public final static Function<HashMap<String, LibraryMember>, List<LibraryMember>> GETALLMEMBERS
			= (hashMap) -> hashMap.entrySet()
						.stream()
						.map(x -> x.getValue())
						.collect(Collectors.toList());
}