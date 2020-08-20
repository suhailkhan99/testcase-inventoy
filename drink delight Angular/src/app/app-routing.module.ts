import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { InventoryComponent } from './inventory/inventory.component';
import { AddinventorytxnComponent } from './inventory/addinventorytxn/addinventorytxn.component';
import { ViewAllComponent } from './inventory/view-all/view-all.component';
import { TxnbydateComponent } from './inventory/txnbydate/txnbydate.component';
 import { VendortxnComponent } from './inventory/vendortxn/vendortxn.component';
import { ViewtxnByProductComponent } from './inventory/viewtxn-by-product/viewtxn-by-product.component';



const routes: Routes = [
  { path:"", component: InventoryComponent, children: 
    [
      { path: '', redirectTo: '', pathMatch: 'full' },
      {path:"add", component: AddinventorytxnComponent},
      {path:"all", component:ViewAllComponent},
      {path:"viewByvendorId", component:VendortxnComponent},
      {path:"viewByDate", component:TxnbydateComponent},
      {path:"viewByProd", component:ViewtxnByProductComponent}
      
    ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
