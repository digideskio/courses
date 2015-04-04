public class AvlTree<AnyType extends Comparable<? super AnyType>> {

    public AvlTree( ) {
        root = null;
    }

    public void insert( AnyType x ) {
        root = insert( x, root );
    }

    public void remove( AnyType x ) {
        root = remove( x, root );
    }

    public AnyType find(AnyType x, AvlNode<AnyType> t) {
        if(t == null)
            return null; 
            
        int compareResult = x.compareTo(t.element);
            
        if(compareResult < 0)
            return find(x, t.left);
        else if(compareResult > 0)
            return find(x, t.right);
        else
            return t.element;
    }

    private AvlNode<AnyType> remove( AnyType x, AvlNode<AnyType> t ) {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }

    public boolean contains( AnyType x ) {
        return contains( x, root );
    }

    public void makeEmpty( ) {
        root = null;
    }

    public boolean isEmpty( ) {
        return root == null;
    }

    public void printTree( ) {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    private static final int ALLOWED_IMBALANCE = 1;

    // Assume t is either balanced or within one of being balanced
    private AvlNode<AnyType> balance( AvlNode<AnyType> t ) {
        if( t == null )
            return t;
        
        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }

    public void checkBalance( ) {
        checkBalance( root );
    }

    private int checkBalance( AvlNode<AnyType> t ) {
        if( t == null )
            return -1;
        
        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }
        return height( t );
    }

    private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t ) {
        if( t == null )
            return new AvlNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
        
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return balance( t );
    }

    private AvlNode<AnyType> findMin( AvlNode<AnyType> t ) {
        if( t == null )
            return t;
        while( t.left != null )
            t = t.left;
        return t;
    }

    private boolean contains( AnyType x, AvlNode<AnyType> t ) {
        while( t != null ) {
            int compareResult = x.compareTo( t.element );
            
            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }
        return false;   // No match
    }

    private void printTree( AvlNode<AnyType> t ) {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    private int height( AvlNode<AnyType> t ) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 ) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 ) {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 ) {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 ) {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlNode<AnyType> {
        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt ) {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

    AvlNode<AnyType> root;
}