package com.aoi.core.game.gobang;

import com.aoi.IntegrationTest;
import com.aoi.core.game.GameEnum;
import com.aoi.core.game.GameExecutor;
import com.aoi.core.game.context.EndContext;
import com.aoi.core.game.gobang.context.GobangStartContext;
import com.aoi.core.game.gobang.context.GobangStepContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class GobangTest {
    @Autowired
    private GameExecutor executor;

    @Test
    public void test() {
        int id = 1;
        executor.newGame(new GobangStartContext(id, 0, 1));
        executor.newGame(new GobangStartContext(2, 0, 1));
        executor.newGame(new GobangStartContext(3, 0, 1));
        executor.endGame(GameEnum.gobang.getCode(), 3, new EndContext());
        executor.newGame(new GobangStartContext(4, 0, 1));
        executor.newGame(new GobangStartContext(5, 0, 1));

        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(0, 0, 0));
        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(1, 1, 0));

        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(0, 0, 1));
        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(1, 1, 1));

        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(0, 0, 2));
        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(1, 1, 2));

        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(0, 0, 3));
        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(1, 1, 3));

        executor.step(GameEnum.gobang.getCode(), id, new GobangStepContext(0, 0, 4));
        executor.newGame(new GobangStartContext(6, 0, 1));

    }
}
