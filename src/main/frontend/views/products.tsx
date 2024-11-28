import { Grid, GridSortColumn} from '@vaadin/react-components';
import { useSignal } from '@vaadin/hilla-react-signals';
import type { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect } from 'react';
import { NavLink} from 'react-router-dom';
import { ProductEndpoint } from 'Frontend/generated/endpoints';
import Product from 'Frontend/generated/com/hyvercode/monolithdays/product/model/entity/Product';

export const config: ViewConfig = {
  menu: {
    title: 'Products'
  },
};

export default function Products() {
    const product= useSignal<Product[]>([]);

    async function fetchProducts() {
        product.value = await ProductEndpoint.findAll();
        console.log(product);
    }

    useEffect(() => {
        fetchProducts();
    }, []);

    return (
        <div className="p-m flex flex-col gap-m">
            <h2>Products</h2>
            <NavLink to={`/product/create`}>Create</NavLink>
            <Grid items={product.value}>
                <GridSortColumn path="productCode">
                    {({ item }) => <NavLink to={`/product/${item.productId}/edit`}>{item.productCode}</NavLink>}
                </GridSortColumn>
                <GridSortColumn path="productName" />
                <GridSortColumn path="price" />
            </Grid>
        </div>
    );
}
