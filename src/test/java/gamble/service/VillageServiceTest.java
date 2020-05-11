package gamble.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import gamble.config.Config;
import gamble.entities.Player;
import gamble.service.PlayerService;
import gamble.service.VillageService;
import gamble.service.io.Inputter;
import gamble.service.io.VillageOutputter;

@RunWith(MockitoJUnitRunner.class)
public class VillageServiceTest {

    @InjectMocks
    private VillageService vs;
    
    @Mock
    private Inputter inputterMock;
    
    @Mock
    private PlayerService playerServiceMock;
    
    @Mock
    private VillageOutputter voMock;
    
    @Test
    public void multiplierReset() {
        
        // Given
        Player p = new Player();
        p.multiplier = 11;
        when(inputterMock.yesOrNo()).thenReturn(true);
        
        // When
        vs.villageVisit(p, Config.targetGold);
        
        // Then
        assertEquals(1, p.multiplier);
    }
    
    @Test
    public void testNotWon() {
        // Given
        Player p = new Player();
        p.gold = 1;
        when(inputterMock.yesOrNo()).thenReturn(true);
        
        // When
        vs.villageVisit(p, 5);
        
        // Then
        verify(voMock).backToBattle();
    }
    
    // Tricky to test winning as it exits the JVM by design
}
