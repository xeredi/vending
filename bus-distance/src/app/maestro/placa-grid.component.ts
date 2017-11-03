import { Component, OnInit } from '@angular/core';

import { Router, ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { PlacaService } from './placa.service';

@Component( {
    selector: 'app-placa-grid',
    templateUrl: './placa-grid.component.html'
} )
export class PlacaGridComponent implements OnInit {
    model: any;
    resultList: any;
    page: number;
    pageSize: number;

    constructor(
        private route: ActivatedRoute
        , private router: Router
        , private location: Location
        , private modelService: PlacaService
    ) {
    }

    ngOnInit() {
        this.pageSize = 20;

        this.route.params.subscribe
            (( params: Params ) => {
                this.page = params['page'] ? +params['page'] : 1;
                this.model = params['model'] ? JSON.parse( params['model'] ) : {};

                this.doSearch();
            } );
    }

    trackByFn( index, item ) {
        return index;
    }

    pageChange() {
        this.doSearch();
    }

    private doSearch() {
        this.modelService.listPage( this.model, this.page, this.pageSize ).subscribe( resp => {
            this.model = resp.model;
            this.resultList = resp.resultList;

            this.location.replaceState( "/maestro/placa/grid;page=" + this.page + ";model=" + encodeURIComponent( JSON.stringify( this.model ) ) );
        } );
    }
}