package com.example.demo.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandRegistry {
    // "ADD_SONG",AddSongCommand
    private static final Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(String commandKeyword, ICommand command) {
        commands.putIfAbsent(commandKeyword,command);
    }

    public void unRegisterCommand(String commandKeyword) {
        commands.remove(commandKeyword);
    }

    private ICommand get(String commandName){
        return commands.get(commandName);
    }

    private List<String> parse(String input){
        return Arrays.stream(input.split(" ")).collect(Collectors.toList());
    }


    // "ADD_SONG Song_1 Artist_1 Album_1 Genre_1"
    public void invokeCommand(String input) {
        
        
        // [ADD_SONG,Song_1,Artist_1,Album_1,Genre_1]
        List<String> tokens = parse(input);
        
        ICommand command = get(tokens.get(0));
        
        if(command == null){
            // Handle Exception
            throw new RuntimeException("INVALID COMMAND 🛑");
        } 
        command.invoke(tokens);
        return;
    }
}
