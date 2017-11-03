import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PlacaGridComponent } from './placa-grid.component';
import { PlacaDetailComponent } from './placa-detail.component';
import { PlacaEditComponent } from './placa-edit.component';
import { RutaGridComponent } from './ruta-grid.component';
import { RutaDetailComponent } from './ruta-detail.component';
import { RutaEditComponent } from './ruta-edit.component';

const routes: Routes = [
    { path: 'placa/grid', component: PlacaGridComponent }
    , { path: 'placa/detail/:id', component: PlacaDetailComponent }
    , { path: 'placa/edit/:action', component: PlacaEditComponent }
    , { path: 'placa/edit/:action/:id', component: PlacaEditComponent }
    , { path: 'ruta/grid', component: RutaGridComponent }
    , { path: 'ruta/detail/:id', component: RutaDetailComponent }
    , { path: 'ruta/edit/:action/:id', component: RutaEditComponent }
];

@NgModule( {
    imports: [RouterModule.forChild( routes )],
    exports: [RouterModule]
} )
export class MaestroRoutingModule { }
