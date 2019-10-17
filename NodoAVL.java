/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol;

/**
 *
 * @author aguil
 */
public class NodoAVL <T extends Comparable<T>>{

    int facteq;
    T element;
    NodoAVL<T> izq, der, papa;
    
    public NodoAVL(T elem) {
        izq = null;
        der = null;
        papa = null;
        this.element = elem;
    }
    
    public T getElem()
    {
        return element;
    }
    public void setfacteq(int fact)
    {
        facteq = fact;
    }
    
    public void setIzq(NodoAVL<T> nodo)
    {
        izq = nodo;
    }
     public void setDer(NodoAVL<T> nodo)
    {
        der = nodo;
    }
     
     public void setpapa(NodoAVL<T> nodo)
     {
         papa = nodo;
     }
    
    public void cuelga(NodoAVL<T> n)
    {
        if(n == null)
            return;
        if(n.getElem().compareTo(element)<0)
        {
            izq =n;
        }
        else
            der = n;
        
        n.setpapa(this);
    }
    
    public NodoAVL<T> getIzq()
    {
        return izq;
    }
    
    public NodoAVL<T> getDer()
    {
        return der;
    }
    
    public NodoAVL<T> getPapa()
    {
        return papa;
    }
    
    public void setElem(T elem)
    {
        this.element = elem;
    }
    
    /*public int altura(){  
        int izq, der;
        if (getIzq() == null)
            izq = 0;
        else
            izq = getIzq().altura();
        if (getDer() == null)
            der = 0;
        else
            der = getDer().altura();
        int res = (izq < der) ? der : izq;
        return res + 1;
    }//method
    */
    
    /*public int altura()
    {
        if(izq == null && der == null)
        {
            return 1;
        }
        else
        {
            /*if(der != null && izq != null)
            {
                if(der.altura() >= izq.altura())
                    return der.altura()+1;
                else
                    return izq.altura()+1;
            }
            else
            {
                if(der == null)
                {
                    return izq.altura()+1;
                }
                else
                {
                    return der.altura()+1;
                }
            }
            
            int numI, numD;
            if(der == null)
            {
                numD = 0;
                numI = izq.altura();
            }
            else
            {
                numI = 0;
                numD = der.altura();
            }
            
            if(numI>numD)
            {
                return numI+1;
            }
            else
            {
                return numD+1;
            }
        }
    }*/
    
}
