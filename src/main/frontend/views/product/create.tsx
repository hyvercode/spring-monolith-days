import { Button, FormLayout, Notification, RadioButton, RadioGroup, Select, TextField } from '@vaadin/react-components';
import { useSignal } from '@vaadin/hilla-react-signals';
import type { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ProductRequestModel from 'Frontend/generated/com/hyvercode/monolithdays/product/request/ProductRequestModel';
import { useForm } from '@vaadin/hilla-react-form';
import { ProductEndpoint } from 'Frontend/generated/endpoints';

export const config: ViewConfig = {
  menu: {
    title: 'Product Create',
    exclude: true
  },
};

export default function ProductCreate() {
  const navigate = useNavigate();

  const { field, model, submit} = useForm(ProductRequestModel, {
    onSubmit: async (product) => {
      await ProductEndpoint.store(product);
      navigate('/products');
    },
  });

  const responsiveSteps = [
    { minWidth: '0', columns: 1 },
    { minWidth: '500px', columns: 2 },
  ];

  return (
    <div className="flex flex-col items-start gap-m">
      <FormLayout responsiveSteps={responsiveSteps}>
        <TextField label="Product Code" {...field(model.productCode)} />
        <TextField label="Barcode" {...field(model.barcode)} />
        <TextField label="SKU" {...field(model.sku)} />
        <TextField label="Product Name" {...field(model.productName)} />
        <TextField label="Price" {...field(model.price)} />
        <TextField label="Stock" {...field(model.stock)} />
        <RadioGroup label="Is Active" {...field(model.isActive)}>
          <RadioButton value="'true'" label="Active" checked />
          <RadioButton value="'false'" label="Inactive" />
        </RadioGroup>
        <div className="flex gap-s">
          <Button onClick={submit} theme="primary">
            Save
          </Button>
          <Button onClick={() => navigate('/products')} theme="primary warning">
            Cancel
          </Button>
        </div>
      </FormLayout>
    </div>
  );
}
