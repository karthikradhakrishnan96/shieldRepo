package com.endurance.shield.dbserver.users;
import com.endurance.shield.dbserver.todoList.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Squad{
        AVENGERS{
                @Override
                public List<Type> getTypes() {
                        return Collections.singletonList(Type.AVENGERS);
                }
        },
        XMEN{
                @Override
                public  List<Type> getTypes() {
                        return Collections.singletonList(Type.XMEN);
                }
        },
        BOTH{
                @Override
                public  List<Type> getTypes() {
                        return Arrays.asList(Type.AVENGERS,Type.XMEN);
                }
        };
        public abstract List<Type> getTypes();
        }
