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
public class ArbolAVL<T extends Comparable<T>> extends BST<T> {
    
    NodoAVL raiz;
    
    public void agrega(T elem)
    {
        NodoAVL<T> camino = raiz;
        NodoAVL<T> ant = raiz;
        NodoAVL<T> rota;
        
        
        while(camino != null)
        {
            if(elem.compareTo(camino.getElem()) < 0)
            {
                ant = camino;
                camino = camino.getIzq();
            }
            else
            {
                ant = camino;
                camino = camino.getDer();
            }
        }
        
        
        NodoAVL<T> nuevo = new NodoAVL(elem);
        if(raiz != null)
        {
            if(ant.getElem().compareTo(elem)> 0)
            {
                ant.setIzq(nuevo);
            }
            else
                ant.setDer(nuevo);
            
            nuevo.setpapa(ant);
            rota = actualizafq(nuevo);//ant
        }
        else
        {
            raiz = nuevo;
            rota = nuevo;
        }
        nuevo.facteq = 0;
        
        if(rota != null)
        {
            if(rota.facteq == 2 || rota.facteq == -2)
            {
                balanceo(rota, rota.facteq);
            }
        }
    }
    
    public NodoAVL<T> actualizafq(NodoAVL<T> nodo)
    {
        int nuevfact;
        if(nodo.getIzq() != null && nodo.getDer()!= null)
        {
            nuevfact = nodo.getDer().altura()-nodo.getIzq().altura();
            
        }
        else
        {
            if(nodo.getIzq() == null && nodo.getDer() == null)
            {
                nuevfact = 0;
            }
            else
            {
                if(nodo.getIzq() == null)
                {
                    nuevfact = nodo.getDer().altura();
                }
                else
                    nuevfact = -nodo.getIzq().altura();
            }
        }
        nodo.setfacteq(nuevfact); 
        if(nodo == raiz || nuevfact == 2 || nuevfact == -2)
        {
            System.out.println("sale en este nodo"+nodo.getElem()+" con este factor"+ nodo.facteq);
            return nodo;
        }
        else
        {
            return actualizafq(nodo.getPapa());
        }
    }
    
    private NodoAVL<T> getInorden(NodoAVL<T> nodo)
    {
        while(nodo.getIzq() != null)
        {
            nodo = nodo.getIzq();
        }
        
        return nodo;
    }
    
    public void balanceo(NodoAVL<T> nodo, int fact)
    {
        System.out.println("balanceop1"+fact);
        NodoAVL<T> aux;
        if(fact == 2)
        {
            System.out.println("balanceop2"+ nodo.getDer().facteq);
            if(nodo.getDer().facteq == 1)//si nodo es raiz problema
            {
                aux = nodo.getDer();
                aux.setpapa(nodo.getPapa());
                if(nodo != raiz)
                    nodo.getPapa().setDer(aux);
                if(aux.getIzq() != null)
                    nodo.setDer(nodo.getDer().getIzq());
                aux.setIzq(nodo);
                nodo.setpapa(aux);
                if(aux.getIzq()!= null)
                    nodo.getDer().setpapa(nodo);
                
            }
            else
            {
                aux = nodo.getDer().getIzq();
                if(aux.getDer()!= null)
                {
                    nodo.getDer().setIzq(aux.getDer());
                    aux.getDer().setpapa(aux.getPapa());
                }
                aux.setDer(aux.getPapa());
                if(aux.getIzq()!= null)
                {
                    nodo.setDer(aux.getIzq());
                    aux.getIzq().setpapa(nodo);
                }
                aux.setIzq(nodo);
                aux.setpapa(nodo.getPapa());
                nodo.setpapa(aux);
            }
        }
        else
        {
            System.out.println("balanceop2"+ nodo.getIzq().facteq);
            if(nodo.getIzq().facteq == -1)
            {
                aux = nodo.getIzq();
                aux.setpapa(nodo.getPapa());
                if(nodo != raiz)
                    nodo.getPapa().setIzq(aux);
                if(aux.getDer() != null)
                    nodo.setIzq(nodo.getIzq().getDer());
                aux.setDer(nodo);
                nodo.setpapa(aux);
                if(aux.getIzq() != null)
                    nodo.getIzq().setpapa(nodo);
            }
            else
            {
                aux = nodo.getIzq().getDer();
                if(aux.getDer()!= null)
                {
                    nodo.getIzq().setDer(aux.getIzq());
                    aux.getIzq().setpapa(aux.getPapa());
                }
                aux.setIzq(aux.getPapa());
                if(aux.getIzq()!= null)
                {
                    nodo.setIzq(aux.getDer());
                    aux.getDer().setpapa(nodo);
                }
                aux.setDer(nodo);
                aux.setpapa(nodo.getPapa());
                nodo.setpapa(aux);
            }
        }
    }
    
    public T elimina(T elem)
    {
        NodoAVL<T> rota;
        NodoAVL<T> aux = this.busca(elem);
        
        if(aux != null)
        {
            
        if(aux.getDer() == null && aux.getIzq()== null)
        {
            if(aux.getPapa().getElem().compareTo(elem) <0)
            {
                aux.getPapa().setDer(null);
            }
            else
                aux.getPapa().setIzq(null);
            
            
            rota = this.actualizafq(aux.getPapa());
            while(rota.getPapa()!= null)
            {
            if(rota != null)
            {
                if(rota.facteq == 2 || rota.facteq == -2)
                {
                    balanceo(rota, rota.facteq);
                }
            }
            rota = this.actualizafq(rota);
            }
            
            aux.setpapa(null);
        }
        else
        {
            NodoAVL<T> inorden = this.getInorden(aux);
            
            inorden.setDer(aux.getDer());
            inorden.setIzq(aux.getIzq());
            
            rota = actualizafq(inorden.getPapa());
            
            if(inorden.getPapa().getElem().compareTo(elem) <0)
            {
                inorden.getPapa().setDer(null);
            }
            else
                inorden.getPapa().setIzq(null);
            
        
            while(rota.getPapa()!= null)
            {
            if(rota != null)
            {
                if(rota.facteq == 2 || rota.facteq == -2)
                {
                    balanceo(rota, rota.facteq);
                }
            }
            rota = this.actualizafq(rota);
            }
            
            inorden.setpapa(aux.getPapa());
            aux.setpapa(null);
        }
        }
        
        return aux==null? null:aux.element;
    }
    
    
    public void imprimeNiveles()
    {
        System.out.println(raiz.getElem());
        imprimeNiveles(raiz);
    }
    private void imprimeNiveles(NodoAVL<T> nodo)
    {
        if(nodo.getIzq()!= null)
        {
            System.out.println(nodo.getIzq().getElem().toString()+" altura"+nodo.getIzq().altura());
            
            
            if(nodo.getDer() != null)
            {
                 System.out.println(nodo.getDer().getElem().toString()+" altura"+nodo.getDer().altura());
                 imprimeNiveles(nodo.getIzq());
                 imprimeNiveles(nodo.getDer());
            }
            else
                imprimeNiveles(nodo.getIzq());
        }
        else
        {
            if(nodo.getDer()!= null)
            {
                System.out.println(nodo.getDer().getElem().toString()+" altura"+nodo.getDer().altura());
                imprimeNiveles(nodo.getDer());
            }
        }
    }
    
    public NodoAVL<T> busca(T elem)//checar que pez con el comparable
    {
        NodoAVL<T> aux = raiz;
        NodoAVL<T> resp = null;
        boolean sal = false;
        
        if(aux.getElem().equals(elem))
            sal = true;
        else
        {
            if(elem.compareTo(aux.getElem())<0)
                aux = aux.getIzq();
            else
                aux = aux.getDer();
        }
        
        while(!elem.equals(aux.getElem())&& !sal)
        {
            if(aux.getPapa().getElem().compareTo(elem)<0 && aux.getElem().compareTo(elem)>0)
                sal = true;
            else
            {
                if(aux.getPapa().getElem().compareTo(elem)>0 && aux.getElem().compareTo(elem)<0)
                    sal = true;
            }
                
            if(!sal)
            {
                if(elem.compareTo(aux.getElem()) < 0)
                {
                    aux = aux.getIzq();
                }
                else
                {
                    aux = aux.getDer();
                }
            }
            
        }
        
        if(elem.equals(aux.getElem()))
        {
            resp = aux;
        }
        
        
        return resp;
    }
    
    
}
