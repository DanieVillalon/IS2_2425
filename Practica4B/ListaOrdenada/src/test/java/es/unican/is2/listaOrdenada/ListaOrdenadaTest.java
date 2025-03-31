package es.unican.is2.listaOrdenada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.ListaOrdenada.ListaOrdenada;

public class ListaOrdenadaTest {
	
	private ListaOrdenada<Integer> sut;
	
	@BeforeEach
	public void setUp() {
		sut = new ListaOrdenada<>();
	}

  
    @Test
    public void testAddSizeGet() {

    	// CP 11
        assertEquals(0, sut.size());
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(0)); //CP 2
        
        //CP 5
        sut.add(4);
        assertEquals(1, sut.size());
        assertEquals(4, sut.get(0));
        
        //CP 4
        sut.add(3);
        assertEquals(2, sut.size());	//CP 10
        assertEquals(3, sut.get(0));	//CP 1
        assertEquals(4, sut.get(1));	
        
        // [3, 4]

        //CP 3
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(4));
        
        //CP 14
        assertThrows(IndexOutOfBoundsException.class, () -> sut.get(-1));
        
        
        // CP 15
        assertThrows(NullPointerException.class, () -> sut.add(null));
    }
    
    
    @Test
    public void testRemove() {
    	
    	//CP 8
    	assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(0));
    	
    	//CP 9
    	sut.add(1);
    	assertEquals(1, sut.remove(0));
    	assertEquals(0, sut.size());	//Comprueba que la cola queda vacía
    	
    	//CP 6
    	sut.add(3);
    	sut.add(1);
    	sut.add(2);
    	assertEquals(1, sut.remove(0));
    	
    	//CP 7
    	assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(4));

    	//CP 16
    	assertThrows(IndexOutOfBoundsException.class, () -> sut.remove(-1));
    	
    }
    
    
    @Test
    public void testClear() {
    	
    	//CP 13
    	assertEquals(0, sut.size());
    	sut.clear();
    	assertEquals(0, sut.size());
    	
    	//CP 12    	
    	sut.add(2);
    	sut.add(1);
    	sut.add(3);
    	assertEquals(3, sut.size());    	
    	sut.clear();
    	assertEquals(0, sut.size());
    }
}
