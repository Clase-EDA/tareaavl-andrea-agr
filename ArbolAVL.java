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
        
        if(rota != null)
        {
            if(rota.facteq == 2 || rota.facteq == -2)
            {
                balanceo(rota, rota.facteq);
            }
        }
        
        this.imprimeNiveles();
    }
    
    public int alturaa(NodoAVL<T> nodo)
    {
        if(nodo == null)
            return 0;
          return 1+  Math.max(alturaa(nodo.getIzq()), alturaa(nodo.getDer()));
    }
    
    public NodoAVL<T> actualizafq(NodoAVL<T> nodo)
    {
        int nuevfact;
        if(nodo.getIzq() != null && nodo.getDer()!= null)
        {
            nuevfact =alturaa(nodo.getDer())-alturaa(nodo.getIzq());
            
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
                    nuevfact = alturaa(nodo.getDer());
                }
                else
                    nuevfact = -alturaa(nodo.getIzq());
            }
        }
        nodo.setfacteq(nuevfact); 
        if(nodo == raiz || nuevfact == 2 || nuevfact == -2)
        {
            return nodo;
        }
        else
        {
            return actualizafq(nodo.getPapa());
        }
    }
    
    
    public NodoAVL<T> getInorden(NodoAVL<T>nodo)
    {
        return getinorden(nodo.getDer());
    }
    
    private NodoAVL<T> getinorden(NodoAVL<T> nodo)
    {
        while(nodo.getIzq() != null)
        {
            nodo = nodo.getIzq();
        }
        
        return nodo;
    }
    
    public void balanceo(NodoAVL<T> nodo, int fact)
    {
        NodoAVL<T> aux;
        if(fact == 2)
        {
            if(nodo.getDer().facteq == 1 || nodo.getDer().facteq == 0)//si nodo es raiz problema
            {
                aux = nodo.getDer();
                aux.setpapa(nodo.getPapa());
                if(nodo != raiz)
                    nodo.getPapa().setDer(aux);
                if(aux.getIzq() != null)
                    nodo.setDer(nodo.getDer().getIzq());
                else
                    nodo.setDer(null);
                aux.setIzq(nodo);
                nodo.setpapa(aux);
                aux.getIzq().setpapa(aux);
                
                
                if(nodo == raiz)
                    raiz = aux;
                
            }
            else
            {
                aux = nodo.getDer().getIzq();
                if(aux.getDer()!= null)
                {
                    nodo.getDer().setIzq(aux.getDer());
                    aux.getDer().setpapa(aux.getPapa());
                }
                else
                    nodo.getDer().setIzq(null);
                
                aux.setDer(aux.getPapa());
                if(aux.getIzq()!= null)
                {
                    nodo.setDer(aux.getIzq());
                    aux.getIzq().setpapa(nodo);
                }
                else
                    nodo.setDer(null);
                
                aux.setIzq(nodo);
                aux.setpapa(nodo.getPapa());
                
                if(nodo.getPapa().getElem().compareTo(nodo.getElem())<0)
                {
                    nodo.getPapa().setIzq(aux);
                }
                else
                {
                    nodo.getPapa().setDer(aux);
                }
                nodo.setpapa(aux);
                aux.getDer().setpapa(aux);
                
                if(nodo == raiz)
                    raiz = aux;
            }
        }
        else
        {
            if(nodo.getIzq().facteq == -1 || nodo.getIzq().facteq == 0)
            {
                aux = nodo.getIzq();
                aux.setpapa(nodo.getPapa());
                if(nodo != raiz)
                    nodo.getPapa().setIzq(aux);
                if(aux.getDer() != null)
                    nodo.setIzq(nodo.getIzq().getDer());
                else
                    nodo.setIzq(null);
                aux.setDer(nodo);
                nodo.setpapa(aux);
                aux.getDer().setpapa(aux);

                if(nodo == raiz)
                    raiz = aux;
            }
            else
            {
                aux = nodo.getIzq().getDer();
                if(aux.getIzq()!= null)
                {
                    nodo.getIzq().setDer(aux.getIzq());
                    aux.getIzq().setpapa(aux.getPapa());
                }
                else
                    nodo.getIzq().setDer(null);
                
                aux.setIzq(aux.getPapa());
                if(aux.getDer()!= null)
                {
                    nodo.setIzq(aux.getDer());
                    aux.getDer().setpapa(nodo);
                }
                else
                    nodo.setIzq(null);
                
                aux.setDer(nodo);
                aux.setpapa(nodo.getPapa());
                
                if(nodo.getPapa().getElem().compareTo(nodo.getElem())<0)
                {
                    nodo.getPapa().setDer(aux);//-----.setDer
                }
                else
                {
                    nodo.getPapa().setIzq(aux);
                }
                nodo.setpapa(aux);
                aux.getIzq().setpapa(aux);
                
                if(nodo == raiz)
                    raiz = aux;
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
            if(rota != null)
            {
                if(rota.facteq == 2 || rota.facteq == -2)
                {
                    balanceo(rota, rota.facteq);
                }
            }
            
            aux.setpapa(null);
        }
        else
        {
            NodoAVL<T> inorden = this.getInorden(aux);
            NodoAVL<T> pops;
            
            
            //aux.setElem(inorden.getElem());
            
            
            if(inorden.getPapa().getElem().compareTo(inorden.getElem()) <0)
            {
                inorden.getPapa().setDer(null);
            }
            else
                inorden.getPapa().setIzq(null);
            
            aux.setElem(inorden.getElem());
            
            pops = inorden.getPapa();
            inorden.setpapa(null);
            rota = actualizafq(pops);
            if(rota != null)
            {
                if(rota.facteq == 2 || rota.facteq == -2)
                {
                    balanceo(rota, rota.facteq);
                }
            }
        }
        
        }
        
        this.imprimeNiveles();
        return aux==null? null:aux.element;
    }
    
    
    public void imprimeNiveles()
    {
        if(raiz != null)
        {
            System.out.println(raiz.getElem()+" feq: "+ (alturaa(raiz.getDer())-alturaa(raiz.getIzq())));
            imprimeNiveles(raiz);
        }
    }
    private void imprimeNiveles(NodoAVL<T> nodo)
    {
        if(nodo.getIzq()!= null)
        {
            System.out.println(nodo.getIzq().getElem().toString()+" feq: "+ (alturaa(nodo.getIzq().getDer())-alturaa(nodo.getIzq().getIzq())));
            
            
            if(nodo.getDer() != null)
            {
                 System.out.println(nodo.getDer().getElem().toString()+ " feq: "+ (alturaa(nodo.getDer().getDer())- alturaa(nodo.getDer().getIzq())));
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
                System.out.println(nodo.getDer().getElem().toString()+ " feq: "+ (alturaa(nodo.getDer().getDer())- alturaa(nodo.getDer().getIzq())));
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
        
        while(!sal && aux != null && !elem.equals(aux.getElem()))
        {    
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
        
        
        if(aux != null)
        {
            if(elem.equals(aux.getElem()))
            {
                resp = aux;
            }
        }
        
        
        return resp;
    }
    
    
}
