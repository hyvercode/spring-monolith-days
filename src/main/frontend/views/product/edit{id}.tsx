import {Button, FormLayout, RadioButton, RadioGroup, TextField} from "@vaadin/react-components";
import {useEffect} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {useForm} from "@vaadin/hilla-react-form";
import {ProductEndpoint} from "Frontend/generated/endpoints";
import ProductRequestModel from "Frontend/generated/com/hyvercode/monolithdays/product/request/ProductRequestModel";

export default function ProductEdit() {
  const {id} = useParams();
  const navigate = useNavigate();

  const {field, model, submit, read} = useForm(ProductRequestModel, {
    onSubmit: async product => {
      await ProductEndpoint.edit(id,product);
    }
  })

  async function loadProduct(id: string) {
    read(await ProductEndpoint.find(id))
  }

  useEffect(() => {
    if (id) {
      loadProduct(id)
    }
  }, [id]);


  const responsiveSteps = [
    { minWidth: '0', columns: 1 },
    { minWidth: '500px', columns: 2 },
  ];

  return (
    <div className="flex flex-col items-start gap-m">
      <FormLayout responsiveSteps={responsiveSteps}>
        <TextField label="Product Code" {...field(model.productCode)} readonly/>
        <TextField label="Barcode" {...field(model.barcode)} readonly/>
        <TextField label="SKU" {...field(model.sku)} />
        <TextField label="Product Name" {...field(model.productName)} readonly/>
        <TextField label="Price" {...field(model.price)} />
        <TextField label="Stock" {...field(model.stock)} />
        <RadioGroup label="Is Active" {...field(model.isActive)}>
          <RadioButton value="'true'" label="Active" checked />
          <RadioButton value="'false'" label="Inactive" />
        </RadioGroup>
        <div className="flex gap-s">
          <Button onClick={submit} theme="primary">
            Update
          </Button>
          <Button onClick={() => navigate('/products')} theme="primary warning">
            Cancel
          </Button>
        </div>
      </FormLayout>
    </div>
  );
}