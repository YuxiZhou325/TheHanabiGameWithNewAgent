package com.fossgalaxy.games.fireworks.ai.username;


import com.fossgalaxy.games.fireworks.GameRunner;
import com.fossgalaxy.games.fireworks.GameStats;
import com.fossgalaxy.games.fireworks.ai.Agent;
import com.fossgalaxy.games.fireworks.ai.AgentPlayer;
import com.fossgalaxy.games.fireworks.ai.rule.DiscardOldestFirst;
import com.fossgalaxy.games.fireworks.ai.rule.DiscardUselessCard;
import com.fossgalaxy.games.fireworks.ai.rule.ProductionRuleAgent;
import com.fossgalaxy.games.fireworks.ai.rule.TellAnyoneAboutUsefulCard;
import com.fossgalaxy.games.fireworks.ai.rule.random.PlayProbablySafeCard;
import com.fossgalaxy.games.fireworks.ai.rule.wrapper.IfRule;
import com.fossgalaxy.games.fireworks.annotations.AgentBuilderStatic;
import com.fossgalaxy.games.fireworks.players.Player;
import com.fossgalaxy.stats.BasicStats;
import com.fossgalaxy.stats.StatsSummary;

import java.util.Random;

public class yz81966 {

    @AgentBuilderStatic("MyRuleBasedAgent")
    public static Agent buildRuleBased() {
        ProductionRuleAgent pra = new ProductionRuleAgent();

        /* Here the rules should be replace to the ones with highest score in the evaluation of chromosomes */

        pra.addRule(new PlayProbablySafeCard(0.5));
        pra.addRule(new TellAnyoneAboutUsefulCard());
        pra.addRule(new DiscardOldestFirst());

        pra.addRule(new IfRule((i, gameState) -> gameState.getLives()>1, new PlayProbablySafeCard(0.5)));
        //Dispose cards that are useless to gain info tokens since it will tell first otherwise will go randomly discard
        pra.addRule(new IfRule((i, gameState) -> gameState.getInformation()==1, new DiscardUselessCard()));


        return pra;
    }


}
